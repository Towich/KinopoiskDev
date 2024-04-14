package com.towich.kinopoiskDev.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.towich.kinopoiskDev.data.model.FieldModel

@Entity(tableName = "genres_table")
data class GenreEntity(
    @PrimaryKey(autoGenerate = false)
    val genre: String
) {
    fun convertToFieldModel() = FieldModel(genre, "")
}