package com.towich.kinopoiskDev.data.model

data class MovieModel(
    val id: Int,
    val name: String,
    val description: String,
    val ratingKp: Float?,
    val posterPreviewUrl: String?,
    val genres: List<String>
)
