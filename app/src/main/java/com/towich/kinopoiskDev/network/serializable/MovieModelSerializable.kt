package com.towich.kinopoiskDev.network.serializable

import com.towich.kinopoiskDev.data.model.GenreModel
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.model.PosterModel
import com.towich.kinopoiskDev.data.model.RatingModel

data class MovieModelSerializable(
    val id: Int,
    val name: String,
    val description: String,
    val rating: RatingModel?,
    val poster: PosterModel?,
    val genres: List<GenreModel>
) {
    fun convertToMovieModel() = MovieModel(
        id,
        name,
        description,
        rating?.kp,
        poster?.previewUrl,
        genres.map { genreModel -> genreModel.name.replaceFirstChar { it.titlecase() } })
}

data class MovieModelResponseSerializable(
    val docs: List<MovieModelSerializable>
)