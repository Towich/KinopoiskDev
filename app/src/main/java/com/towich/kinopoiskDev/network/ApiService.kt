package com.towich.kinopoiskDev.network

import com.towich.kinopoiskDev.data.source.Constants
import com.towich.kinopoiskDev.network.serializable.MovieModelResponseSerializable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET(ApiRoutes.MOVIES)
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("limit") limit: Int = Constants.pageLimit,
        @Query("selectFields") selectFields: List<String> = Constants.selectedFields
    ): Response<MovieModelResponseSerializable>
}