package com.towich.kinopoiskDev.data.network.serializable

import com.towich.kinopoiskDev.data.model.GenreModel
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.model.PosterModel
import com.towich.kinopoiskDev.data.model.RatingModel
import com.towich.kinopoiskDev.data.model.VotesModel

data class MovieModelRemote(
    val id: Int,
    val name: String,
    val description: String,
    val rating: RatingModel?,
    val poster: PosterModel?,
    val genres: List<GenreModel>,
    val ageRating: Int?,
    val year: Int?,
    val isSeries: Boolean?,
    val votes: VotesModel?,
) {
    fun convertToMovieModel() = MovieModel(
        id = id,
        name = name,
        description = description,
        ratingKp = rating?.kp,
        posterPreviewUrl = poster?.previewUrl,
        genres = genres.map { genreModel -> genreModel.name.replaceFirstChar { it.titlecase() } },
        ageRating = ageRating,
        year = year,
        isSeries = isSeries,
        votesKp = votes?.kp
    )
}

data class MovieModelResponseRemote(
    val docs: List<MovieModelRemote>
)