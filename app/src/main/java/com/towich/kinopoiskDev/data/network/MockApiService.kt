package com.towich.kinopoiskDev.data.network

import com.towich.kinopoiskDev.data.model.FieldModel
import com.towich.kinopoiskDev.data.network.serializable.ActorModelRemote
import com.towich.kinopoiskDev.data.network.serializable.ActorModelResponseRemote
import com.towich.kinopoiskDev.data.network.serializable.MovieModelResponseRemote
import com.towich.kinopoiskDev.data.source.Constants
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

class MockApiService : ApiService {
    override suspend fun getMovies(
        page: Int,
        limit: Int,
        selectFields: List<String>,
        genre: String?,
        country: String?,
        year: String?,
        age: String?
    ): Response<MovieModelResponseRemote> {
        return Response.success(
            MovieModelResponseRemote(
                listOf(Constants.movieRemoteTest)
            )
        )
    }

    override suspend fun getAllPossibleValuesByField(field: String): Response<List<FieldModel>> {
        return when(field){
            Constants.genresField -> {
                Response.success(
                    listOf(
                        FieldModel(name = "комедия", slug = "comedy"),
                        FieldModel(name = "триллер", slug = "triller"),
                        FieldModel(name = "аниме", slug = "anime"),
                    )
                )
            }
            Constants.countriesField -> {
                Response.success(
                    listOf(
                        FieldModel(name = "Австралия", slug = "Avstraliya"),
                        FieldModel(name = "Австрия", slug = "Avstriya"),
                        FieldModel(name = "Азербайджан", slug = "Azerbaydzhan"),
                    )
                )
            }
            else -> {
                Response.error(400, "test_error".toResponseBody("application/json".toMediaTypeOrNull()))
            }
        }
    }

    override suspend fun getActorsByMovieId(
        page: Int,
        limit: Int,
        movieId: String,
        selectFields: List<String>
    ): Response<ActorModelResponseRemote> {
        return Response.success(
            ActorModelResponseRemote(docs = Constants.actorsTest)
        )
    }
}