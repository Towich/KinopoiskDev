package com.towich.kinopoiskDev.data.repository

import androidx.paging.PagingData
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.network.serializable.MovieModelResponseSerializable
import com.towich.kinopoiskDev.util.ApiResult
import kotlinx.coroutines.flow.Flow

interface MainRepository {
//    suspend fun getCategories(): ApiResult<List<String>>
    suspend fun getMovies(): ApiResult<MovieModelResponseSerializable>
    fun getMoviesPage(): Flow<PagingData<MovieModel>>
}