package com.towich.kinopoiskDev.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.towich.kinopoiskDev.data.model.MovieModel

@Entity(tableName = "movies_table")
data class MovieEntity(
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
) {
    fun convertToMovieModel() : MovieModel {
        return MovieModel(
            id, name, description, ratingKp, posterPreviewUrl, genres, ageRating, year, isSeries, votesKp
        )
    }
}