package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.repository.MainRepository

class SetCurrentSeasonUseCase(private val repository: MainRepository) {
    operator fun invoke(seasonNumber: Int?) {
        repository.setCurrentSeason(seasonNumber = seasonNumber)
    }
}