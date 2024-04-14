package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.repository.MainRepository

class GetQueriesCountUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(): Int {
        return repository.getQueriesCount()
    }
}