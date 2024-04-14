package com.towich.kinopoiskDev.data.repository

import android.content.Context
import android.net.ConnectivityManager
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.towich.kinopoiskDev.data.model.ActorModel
import com.towich.kinopoiskDev.data.model.EpisodeModel
import com.towich.kinopoiskDev.data.model.FieldModel
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.model.ReviewModel
import com.towich.kinopoiskDev.data.model.SeasonModel
import com.towich.kinopoiskDev.data.source.Constants
import com.towich.kinopoiskDev.data.source.SessionStorage
import com.towich.kinopoiskDev.data.util.MoviesPagingSource
import com.towich.kinopoiskDev.data.network.ApiService
import com.towich.kinopoiskDev.data.network.serializable.MovieModelResponseRemote
import com.towich.kinopoiskDev.data.network.ApiResult
import com.towich.kinopoiskDev.data.network.serializable.PosterModelResponseRemote
import com.towich.kinopoiskDev.data.room.dao.CountryDao
import com.towich.kinopoiskDev.data.room.dao.GenreDao
import com.towich.kinopoiskDev.data.room.dao.MovieDao
import com.towich.kinopoiskDev.data.room.dao.QueryDao
import com.towich.kinopoiskDev.data.room.entity.MovieEntity
import com.towich.kinopoiskDev.data.room.entity.QueryEntity
import com.towich.kinopoiskDev.data.util.ActorsPagingSource
import com.towich.kinopoiskDev.data.util.EpisodesPagingSource
import com.towich.kinopoiskDev.data.util.ReviewsPagingSource
import com.towich.kinopoiskDev.data.util.SeasonsPagingSource
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage,
    private val movieDao: MovieDao,
    private val genreDao: GenreDao,
    private val countryDao: CountryDao,
    private val queryDao: QueryDao,
    private val connectivityManager: ConnectivityManager,
    private val context: Context
) : MainRepository {

    override suspend fun getMovies(): ApiResult<MovieModelResponseRemote> {
        return try {
            val response = apiService.getMovies(page = 1)

            if(response.isSuccessful){
                ApiResult.Success(response.body() ?: MovieModelResponseRemote(docs = listOf()))
            } else{
                ApiResult.Error(response.message())
            }
        } catch (e: Exception){
            ApiResult.Error(e.message ?: "unknown error")
        }

    }

    override fun getMoviesPage(): Flow<PagingData<MovieModel>> = Pager(
        config = PagingConfig(
            pageSize = Constants.pageLimit,
        ),
        pagingSourceFactory = {
            MoviesPagingSource(apiService, sessionStorage, null, movieDao, connectivityManager, context)
        }
    ).flow

    override suspend fun getGenres(): ApiResult<List<FieldModel>> {
        return if(sessionStorage.listOfGenres != null)
            ApiResult.Success(sessionStorage.listOfGenres!!)
        else{
            try {
                val response = apiService.getAllPossibleValuesByField(field = Constants.genresField)
                if(response.isSuccessful){
                    sessionStorage.listOfGenres = response.body()
                    ApiResult.Success(response.body() ?: listOf())
                } else{
                    ApiResult.Error(response.message())
                }
            } catch (e: Exception){
                ApiResult.Error(e.message ?: "unknown error")
            }
        }
    }

    override suspend fun getCountries(): ApiResult<List<FieldModel>> {
        return if(sessionStorage.listOfCountries != null)
            ApiResult.Success(sessionStorage.listOfCountries!!)
        else{
            try {
                val response =
                    apiService.getAllPossibleValuesByField(field = Constants.countriesField)
                if (response.isSuccessful) {
                    sessionStorage.listOfCountries = response.body()
                    ApiResult.Success(response.body() ?: listOf())
                } else {
                    ApiResult.Error(response.message())
                }
            } catch (e: Exception){
                ApiResult.Error(e.message ?: "unknown error")
            }
        }
    }

    override fun setFilters(listOfFilters: List<String?>) {
        sessionStorage.listOfFilters = listOfFilters
    }

    override fun getFilters(): List<String?> {
        return sessionStorage.listOfFilters
    }

    override fun setCurrentMovie(movie: MovieModel?) {
        sessionStorage.currentMovie = movie
    }

    override fun getCurrentMovie(): MovieModel? {
        return sessionStorage.currentMovie
    }

    override fun getActorsPage(): Flow<PagingData<ActorModel>> = Pager(
        config = PagingConfig(
            pageSize = Constants.pageLimit,
        ),
        pagingSourceFactory = {
            ActorsPagingSource(apiService, sessionStorage)
        }
    ).flow

    override fun getSeasonsPage(): Flow<PagingData<SeasonModel>> = Pager(
        config = PagingConfig(
            pageSize = Constants.pageLimit,
        ),
        pagingSourceFactory = {
            SeasonsPagingSource(apiService, sessionStorage)
        }
    ).flow

    override fun setCurrentSeason(seasonNumber: Int?) {
        sessionStorage.currentSeason = seasonNumber
    }

    override fun getCurrentSeason(): Int? {
        return sessionStorage.currentSeason
    }

    override fun getEpisodesPage(): Flow<PagingData<EpisodeModel>> = Pager(
        config = PagingConfig(
            pageSize = Constants.pageLimit,
        ),
        pagingSourceFactory = {
            EpisodesPagingSource(apiService, sessionStorage)
        }
    ).flow

    override fun getReviewPage(): Flow<PagingData<ReviewModel>>  = Pager(
        config = PagingConfig(
            pageSize = Constants.pageLimit,
        ),
        pagingSourceFactory = {
            ReviewsPagingSource(apiService, sessionStorage)
        }
    ).flow

    override fun searchMovieByName(query: String): Flow<PagingData<MovieModel>> = Pager(
        config = PagingConfig(
            pageSize = Constants.pageLimit,
        ),
        pagingSourceFactory = {
            MoviesPagingSource(
                apiService = apiService,
                sessionStorage = sessionStorage,
                query = query,
                movieDao = movieDao,
                connectivityManager = connectivityManager,
                context = context
            )
        }
    ).flow

    override suspend fun getPosters(): ApiResult<PosterModelResponseRemote> {
        return try {
            val response = apiService.getImages(
                movieId = listOf(sessionStorage.currentMovie?.id.toString())
            )

            if(response.isSuccessful){
                ApiResult.Success(response.body() ?: PosterModelResponseRemote(docs = listOf()))
            } else{
                ApiResult.Error(response.message())
            }
        } catch (e: Exception){
            ApiResult.Error(e.message ?: "unknown error")
        }
    }

    override suspend fun getMoviesFromDb(): List<MovieEntity> {
        return movieDao.getAllMovies(Constants.pageLimit, Constants.pageLimit)
    }

    override suspend fun getMovieByIdFromDb(id: Int): MovieEntity? {
        return movieDao.getMovieById(id)
    }

    override suspend fun searchMoviesByNameInDb(name: String): List<MovieEntity> {
        return movieDao.searchMoviesByName(name)
    }

    override suspend fun insertMovieIntoDb(movie: MovieEntity) {
        movieDao.insertMovie(movie)
    }

    override suspend fun deleteDb(movie: MovieEntity) {
        movieDao.deleteAllMovies()
    }

    override suspend fun getAllQueries(): List<QueryEntity> {
        return queryDao.getAllQueries()
    }

    override suspend fun insertQuery(query: QueryEntity) {
        queryDao.insertQuery(query)
    }

    override suspend fun deleteFirstQuery() {
        queryDao.deleteFirstQuery()
    }

    override suspend fun getQueriesCount(): Int {
        return queryDao.getQueriesCount()
    }

    override suspend fun shiftIds() {
        queryDao.shiftIds()
    }
}