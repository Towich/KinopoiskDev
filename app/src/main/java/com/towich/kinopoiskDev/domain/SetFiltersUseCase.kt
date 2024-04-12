package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.repository.MainRepository

class SetFiltersUseCase(private val repository: MainRepository) {
    operator fun invoke(listOfFilters: List<String?>) {
        return repository.setFilters(listOfFilters = listOfFilters)
    }
}