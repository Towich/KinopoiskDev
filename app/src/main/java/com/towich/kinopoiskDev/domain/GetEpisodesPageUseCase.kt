package com.towich.kinopoiskDev.domain

import androidx.paging.PagingData
import com.towich.kinopoiskDev.data.model.EpisodeModel
import com.towich.kinopoiskDev.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class GetEpisodesPageUseCase(private val repository: MainRepository) {
    operator fun invoke(): Flow<PagingData<EpisodeModel>> {
        return repository.getEpisodesPage()
    }
}