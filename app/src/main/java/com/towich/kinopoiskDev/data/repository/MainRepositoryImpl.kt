package com.towich.kinopoiskDev.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.towich.kinopoiskDev.data.model.ActorModel
import com.towich.kinopoiskDev.data.model.FieldModel
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.source.Constants
import com.towich.kinopoiskDev.data.source.SessionStorage
import com.towich.kinopoiskDev.data.util.MoviesPagingSource
import com.towich.kinopoiskDev.data.network.ApiService
import com.towich.kinopoiskDev.data.network.serializable.MovieModelResponseRemote
import com.towich.kinopoiskDev.data.network.ApiResult
import com.towich.kinopoiskDev.data.util.ActorsPagingSource
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage
) : MainRepository {

    override suspend fun getMovies(): ApiResult<MovieModelResponseRemote> {
        val response = apiService.getMovies(page = 1)
        return if(response.isSuccessful){
            ApiResult.Success(response.body() ?: MovieModelResponseRemote(docs = listOf()))
        } else{
            ApiResult.Error(response.message())
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
            val response = apiService.getAllPossibleValuesByField(field = Constants.genresField)
            if(response.isSuccessful){
                sessionStorage.listOfGenres = response.body()
                ApiResult.Success(response.body() ?: listOf())
            } else{
                ApiResult.Error(response.message())
            }
        }
    }

    override suspend fun getCountries(): ApiResult<List<FieldModel>> {
        return if(sessionStorage.listOfCountries != null)
            ApiResult.Success(sessionStorage.listOfCountries!!)
        else{
            val response = apiService.getAllPossibleValuesByField(field = Constants.countriesField)
            if(response.isSuccessful){
                sessionStorage.listOfCountries = response.body()
                ApiResult.Success(response.body() ?: listOf())
            } else{
                ApiResult.Error(response.message())
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
}