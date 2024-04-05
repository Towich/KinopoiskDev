package com.towich.kinopoiskDev.ui.screen.allmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.domain.GetMoviesPageUseCase
import com.towich.kinopoiskDev.domain.GetMoviesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AllMoviesViewModel @Inject constructor(
    private val getMovies: GetMoviesUseCase,
    private val getMoviesPage: GetMoviesPageUseCase
) : ViewModel() {

    fun performGetMovies(): Flow<PagingData<MovieModel>> = getMoviesPage().cachedIn(viewModelScope)
}