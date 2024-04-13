package com.towich.kinopoiskDev.domain

import androidx.paging.PagingData
import com.towich.kinopoiskDev.data.model.ActorModel
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class GetActorsPageUseCase(private val repository: MainRepository) {
    operator fun invoke(): Flow<PagingData<ActorModel>> {
        return repository.getActorsPage()
    }
}