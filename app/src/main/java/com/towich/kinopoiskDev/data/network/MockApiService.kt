package com.towich.kinopoiskDev.data.network

import com.towich.kinopoiskDev.data.model.FieldModel
import com.towich.kinopoiskDev.data.model.GenreModel
import com.towich.kinopoiskDev.data.model.PosterModel
import com.towich.kinopoiskDev.data.model.RatingModel
import com.towich.kinopoiskDev.data.network.serializable.MovieModelRemote
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
                listOf(
                    MovieModelRemote(
                        id = 3242,
                        name = "Один дома",
                        description = "Американское семейство отправляется из Чикаго в Европу, но в спешке сборов бестолковые родители забывают дома... одного из своих детей. Юное создание, однако, не теряется и демонстрирует чудеса изобретательности. И когда в дом залезают грабители, им приходится не раз пожалеть о встрече с милым крошкой.",
                        rating = RatingModel(kp = 4.5f),
                        poster = PosterModel(
                            url = "https://image.openmoviedb.com/kinopoisk-images/6201401/022a58e3-5b9b-411b-bfb3-09fedb700401/orig",
                            previewUrl = "https://image.openmoviedb.com/kinopoisk-images/6201401/022a58e3-5b9b-411b-bfb3-09fedb700401/x1000"
                        ),
                        genres = listOf(
                            GenreModel("комедия"),
                            GenreModel("семейный"),
                        )
                    )
                )
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
}