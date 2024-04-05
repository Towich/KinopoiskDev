package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.network.serializable.MovieModelResponseSerializable
import com.towich.kinopoiskDev.util.ApiResult

class GetMoviesUseCase(
    private val repository: MainRepository
) {
    suspend operator fun invoke(): ApiResult<MovieModelResponseSerializable> {
        return repository.getMovies()
    }
}