package com.towich.kinopoiskDev.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_favourite_table")
data class MovieFavouriteEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String?,
    val description: String?,
    val ratingKp: Float?,
    val posterPreviewUrl: String?,
    val genres: List<String>,
    val ageRating: Int?,
    val year: Int?,
    val isSeries: Boolean?,
    val votesKp: String?
)