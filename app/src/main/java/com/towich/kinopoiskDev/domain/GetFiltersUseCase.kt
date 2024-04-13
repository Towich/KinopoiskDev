package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.repository.MainRepository

class GetFiltersUseCase(private val repository: MainRepository) {
    operator fun invoke(): List<String?> {
        return repository.getFilters()
    }
}