package com.towich.kinopoiskDev.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.source.Constants
import com.towich.kinopoiskDev.data.source.SessionStorage
import com.towich.kinopoiskDev.data.util.MoviesPagingSource
import com.towich.kinopoiskDev.network.ApiService
import com.towich.kinopoiskDev.network.serializable.MovieModelResponseSerializable
import com.towich.kinopoiskDev.util.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class MainRepositoryImpl(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage
) : MainRepository {
    override suspend fun getMovies(): ApiResult<MovieModelResponseSerializable> {
        val response = apiService.getMovies(page = 1)
        if(response.isSuccessful){
            return ApiResult.Success(response.body() ?: MovieModelResponseSerializable(docs = listOf()))
        }
        else{
            return ApiResult.Error(response.message())
        }
    }

    override fun getMoviesPage(): Flow<PagingData<MovieModel>> = Pager(
        config = PagingConfig(
            pageSize = Constants.pageLimit,
        ),
        pagingSourceFactory = {
            MoviesPagingSource(apiService)
        }
    ).flow

}