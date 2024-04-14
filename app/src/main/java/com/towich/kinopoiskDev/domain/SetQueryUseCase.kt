package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.data.room.entity.QueryEntity

class SetQueryUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(query: QueryEntity) {
        repository.insertQuery(query)
    }
}