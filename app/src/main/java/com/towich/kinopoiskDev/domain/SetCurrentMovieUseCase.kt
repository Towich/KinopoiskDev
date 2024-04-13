package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.data.network.serializable.MovieModelResponseRemote
import com.towich.kinopoiskDev.data.network.ApiResult

class SetCurrentMovieUseCase(private val repository: MainRepository) {
    operator fun invoke(movie: MovieModel?) {
        repository.setCurrentMovie(movie)
    }
}