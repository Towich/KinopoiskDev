package com.towich.kinopoiskDev.data.repository

import com.towich.kinopoiskDev.data.source.SessionStorage
import com.towich.kinopoiskDev.network.ApiService
import com.towich.kinopoiskDev.network.serializable.MovieModelResponseSerializable
import com.towich.kinopoiskDev.util.ApiResult
import kotlinx.coroutines.runBlocking

class MainRepositoryImpl(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage
) : MainRepository {
    init {
        runBlocking {
            getMovies()
        }
    }
    override suspend fun getMovies(): ApiResult<MovieModelResponseSerializable> {
        val response = apiService.getMovies(page = 1)
        if(response.isSuccessful){
            return ApiResult.Success(response.body() ?: MovieModelResponseSerializable(docs = listOf()))
        }
        else{
            return ApiResult.Error(response.message())
        }
    }

}