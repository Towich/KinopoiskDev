package com.towich.kinopoiskDev.data.network

import com.towich.kinopoiskDev.data.model.FieldModel
import com.towich.kinopoiskDev.data.network.serializable.ActorModelResponseRemote
import com.towich.kinopoiskDev.data.network.serializable.EpisodeModelResponseList
import com.towich.kinopoiskDev.data.network.serializable.EpisodeModelResponseRemote
import com.towich.kinopoiskDev.data.network.serializable.MovieModelResponseRemote
import com.towich.kinopoiskDev.data.network.serializable.PosterModelResponseRemote
import com.towich.kinopoiskDev.data.network.serializable.ReviewModelResponseRemote
import com.towich.kinopoiskDev.data.network.serializable.SeasonModelResponseRemote
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
        age: String?,
        isSeries: Boolean?
    ): Response<MovieModelResponseRemote> {
        return Response.success(
            MovieModelResponseRemote(
                listOf(Constants.movieRemoteTest)
            )
        )
    }

    override suspend fun getAllPossibleValuesByField(field: String): Response<List<FieldModel>> {
        return when (field) {
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
                Response.error(
                    400,
                    "test_error".toResponseBody("application/json".toMediaTypeOrNull())
                )
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

    override suspend fun getSeasonsByMovieId(
        page: Int,
        limit: Int,
        movieId: List<String>,
        selectFields: List<String>
    ): Response<SeasonModelResponseRemote> {
        return Response.success(
            SeasonModelResponseRemote(docs = Constants.seasonTest)
        )
    }

    override suspend fun getEpisodes(
        page: Int,
        limit: Int,
        movieId: List<String>,
        number: List<String>,
        selectFields: List<String>
    ): Response<EpisodeModelResponseRemote> {
        return Response.success(
            EpisodeModelResponseRemote(docs = listOf(EpisodeModelResponseList(episodes = Constants.episodesTest)))
        )
    }

    override suspend fun getReviews(
        page: Int,
        limit: Int,
        movieId: List<String>,
        selectFields: List<String>
    ): Response<ReviewModelResponseRemote> {
        return Response.success(
            ReviewModelResponseRemote(docs = Constants.reviewsTest)
        )
    }

    override suspend fun searchMovie(
        page: Int,
        limit: Int,
        query: String
    ): Response<MovieModelResponseRemote> {
        return Response.success(
            MovieModelResponseRemote(
                listOf(Constants.movieRemoteTest)
            )
        )
    }

    override suspend fun getImages(
        page: Int,
        limit: Int,
        movieId: List<String>
    ): Response<PosterModelResponseRemote> {
        return Response.success(
            PosterModelResponseRemote(docs = Constants.postersTest)
        )
    }
}