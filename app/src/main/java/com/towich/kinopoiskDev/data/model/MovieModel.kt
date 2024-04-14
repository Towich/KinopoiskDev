package com.towich.kinopoiskDev.data.model

import com.towich.kinopoiskDev.data.room.entity.MovieEntity

data class MovieModel(
    val id: Int,
    val name: String?,
    val description: String?,
    val ratingKp: Float?,
    val posterPreviewUrl: String?,
    val genres: List<String>,
    val ageRating: Int?,
    val year: Int?,
    val isSeries: Boolean?,
    val votesKp: String?
){
    fun converterToMovieEntity(): MovieEntity {
        return MovieEntity(
            id,
            name,
            description,
            ratingKp,
            posterPreviewUrl,
            genres,
            ageRating,
            year,
            isSeries,
            votesKp
        )
    }
}