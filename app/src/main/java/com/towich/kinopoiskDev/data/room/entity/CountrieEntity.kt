package com.towich.kinopoiskDev.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.towich.kinopoiskDev.data.model.FieldModel

@Entity(tableName = "countries_table")
data class CountryEntity(
    @PrimaryKey(autoGenerate = false)
    val country: String
) {
    fun convertToFieldModel() = FieldModel(country, "")
}