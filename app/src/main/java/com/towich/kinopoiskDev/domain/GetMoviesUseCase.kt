package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.data.network.serializable.MovieModelResponseRemote
import com.towich.kinopoiskDev.data.network.ApiResult

class GetMoviesUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(): ApiResult<MovieModelResponseRemote> {
        return repository.getMovies()
    }
}