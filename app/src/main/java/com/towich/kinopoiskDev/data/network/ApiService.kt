package com.towich.kinopoiskDev.data.network

import com.towich.kinopoiskDev.data.model.FieldModel
import com.towich.kinopoiskDev.data.network.serializable.ActorModelRemote
import com.towich.kinopoiskDev.data.network.serializable.ActorModelResponseRemote
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
        @Query("selectFields") selectFields: List<String> = Constants.movieSelectedFields,
        @Query("genres.name") genre: String? = null,
        @Query("countries.name") country: String? = null,
        @Query("year") year: String? = null,
        @Query("ageRating") age: String? = null
    ): Response<MovieModelResponseRemote>

    @GET(ApiRoutes.POSSIBLE_VALUES_BY_FIELD)
    suspend fun getAllPossibleValuesByField(
        @Query("field") field: String
    ): Response<List<FieldModel>>

    @GET(ApiRoutes.ACTORS)
    suspend fun getActorsByMovieId(
        @Query("page") page: Int,
        @Query("limit") limit: Int = Constants.pageLimit,
        @Query("movies.id") movieId: String,
        @Query("selectFields") selectFields: List<String> = Constants.actorSelectedFields,
    ): Response<ActorModelResponseRemote>
}