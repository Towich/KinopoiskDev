package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.model.FieldModel
import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.data.network.serializable.MovieModelResponseRemote
import com.towich.kinopoiskDev.data.network.ApiResult

class GetGenresUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(): ApiResult<List<FieldModel>> {
        return repository.getGenres()
    }
}