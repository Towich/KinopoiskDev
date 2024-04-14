package com.towich.kinopoiskDev.data.repository

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
import com.towich.kinopoiskDev.data.util.ActorsPagingSource
import com.towich.kinopoiskDev.data.util.EpisodesPagingSource
import com.towich.kinopoiskDev.data.util.ReviewsPagingSource
import com.towich.kinopoiskDev.data.util.SeasonsPagingSource
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage
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
            MoviesPagingSource(apiService, sessionStorage)
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
                query = query
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
}