package com.towich.kinopoiskDev.data.repository

import com.towich.kinopoiskDev.network.serializable.MovieModelResponseSerializable
import com.towich.kinopoiskDev.util.ApiResult

interface MainRepository {
//    suspend fun getCategories(): ApiResult<List<String>>
    suspend fun getMovies(): ApiResult<MovieModelResponseSerializable>
}