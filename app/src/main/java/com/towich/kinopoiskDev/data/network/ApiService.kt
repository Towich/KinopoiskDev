package com.towich.kinopoiskDev.data.network

import com.towich.kinopoiskDev.data.model.FieldModel
import com.towich.kinopoiskDev.data.source.Constants
import com.towich.kinopoiskDev.data.network.serializable.MovieModelResponseRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ApiRoutes.MOVIES)
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("limit") limit: Int = Constants.pageLimit,
        @Query("selectFields") selectFields: List<String> = Constants.selectedFields
    ): Response<MovieModelResponseRemote>

    @GET(ApiRoutes.POSSIBLE_VALUES_BY_FIELD)
    suspend fun getAllPossibleValuesByField(
        @Query("field") field: String
    ): Response<List<FieldModel>>
}