package com.towich.kinopoiskDev.ui.screen.reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.towich.kinopoiskDev.data.model.ReviewModel
import com.towich.kinopoiskDev.domain.GetReviewsPageUseCase
import kotlinx.coroutines.flow.Flow

class ReviewsViewModel(
    private val getReviewsPage: GetReviewsPageUseCase
) : ViewModel() {

    fun performGetReviews(): Flow<PagingData<ReviewModel>> = getReviewsPage().cachedIn(viewModelScope)
}