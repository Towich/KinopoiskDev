package com.towich.kinopoiskDev.ui.screen.reviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.towich.kinopoiskDev.domain.GetReviewsPageUseCase

class ReviewsViewModel(
    private val getReviewsPage: GetReviewsPageUseCase
) : ViewModel() {

    val reviews = getReviewsPage().cachedIn(viewModelScope)
}