package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.repository.MainRepository

class ShiftIdsUseCase(private val repository: MainRepository) {
    suspend operator fun invoke() {
        return repository.shiftIds()
    }
}