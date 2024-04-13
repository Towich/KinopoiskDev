package com.towich.kinopoiskDev.domain

import androidx.paging.PagingData
import com.towich.kinopoiskDev.data.model.SeasonModel
import com.towich.kinopoiskDev.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class GetSeasonsPageUseCase(private val repository: MainRepository) {
    operator fun invoke(): Flow<PagingData<SeasonModel>> {
        return repository.getSeasonsPage()
    }
}