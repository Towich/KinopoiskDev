package com.towich.kinopoiskDev.ui.screen.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.towich.kinopoiskDev.data.model.ActorModel
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.domain.GetActorsPageUseCase
import com.towich.kinopoiskDev.domain.GetCurrentMovieUseCase
import com.towich.kinopoiskDev.domain.SetCurrentMovieUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieViewModel(
    private val getCurrentMovie: GetCurrentMovieUseCase,
    private val getActorsPage: GetActorsPageUseCase,
) : ViewModel() {

    fun performGetActors(): Flow<PagingData<ActorModel>> = getActorsPage().cachedIn(viewModelScope)

    fun performGetCurrentMovie(): MovieModel? {
        return getCurrentMovie()
    }
}