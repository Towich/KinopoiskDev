package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.data.room.entity.QueryEntity

class GetQueriesUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(): List<QueryEntity> {
        return repository.getAllQueries()
    }
}