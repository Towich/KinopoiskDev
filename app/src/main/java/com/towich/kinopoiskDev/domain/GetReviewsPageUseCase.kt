package com.towich.kinopoiskDev.domain

import androidx.paging.PagingData
import com.towich.kinopoiskDev.data.model.ReviewModel
import com.towich.kinopoiskDev.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class GetReviewsPageUseCase(private val repository: MainRepository) {
    operator fun invoke(): Flow<PagingData<ReviewModel>> {
        return repository.getReviewPage()
    }
}