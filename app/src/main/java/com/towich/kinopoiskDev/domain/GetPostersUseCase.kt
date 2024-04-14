package com.towich.kinopoiskDev.domain

import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.data.network.serializable.MovieModelResponseRemote
import com.towich.kinopoiskDev.data.network.ApiResult
import com.towich.kinopoiskDev.data.network.serializable.PosterModelResponseRemote

class GetPostersUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(): ApiResult<PosterModelResponseRemote> {
        return repository.getPosters()
    }
}