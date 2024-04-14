package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.repository.MainRepository

class DeleteFirstQueryUseCase(private val repository: MainRepository) {
    suspend operator fun invoke() {
        repository.deleteFirstQuery()
    }
}