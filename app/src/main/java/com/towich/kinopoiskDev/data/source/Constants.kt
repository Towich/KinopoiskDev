package com.towich.kinopoiskDev.data.source

import com.towich.kinopoiskDev.BuildConfig

object Constants {
    const val API_KEY: String = BuildConfig.API_KEY_KINOPOISK_DEV
    const val pageLimit = 20

    const val genresField = "genres.name"
    const val countriesField = "countries.name"

    val selectedFields = listOf("id", "name", "description", "rating", "poster", "genres", "year", "ageRating")
}