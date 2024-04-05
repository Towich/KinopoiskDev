package com.towich.kinopoiskDev.domain

import androidx.paging.PagingData
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.network.serializable.MovieModelResponseSerializable
import com.towich.kinopoiskDev.util.ApiResult
import kotlinx.coroutines.flow.Flow

class GetMoviesPageUseCase(
    private val repository: MainRepository
) {
    operator fun invoke(): Flow<PagingData<MovieModel>> {
        return repository.getMoviesPage()
    }
}