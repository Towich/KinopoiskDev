package com.towich.kinopoiskDev.data.model

data class RatingModel(
    val kp: Float,
    val imdb: Float,
    val tmdb: Float,
    val filmCritics: Float,
    val russianFilmCritics: Float,
    val await: Float,
)
