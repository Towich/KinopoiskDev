package com.towich.kinopoiskDev.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.domain.GetMoviesUseCase
import com.towich.kinopoiskDev.data.network.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val getMovies: GetMoviesUseCase
) : ViewModel() {

    private val _pagerMovies = MutableStateFlow<List<MovieModel>>(listOf())
    val pagerMovies: StateFlow<List<MovieModel>> = _pagerMovies

    init {
        performGetPagerMovies()
    }

    private fun performGetPagerMovies(){
        viewModelScope.launch {

            when (val result = getMovies()) {
                is ApiResult.Success -> {
                    _pagerMovies.value = result.data.docs.map { it.convertToMovieModel() }
                }

                is ApiResult.Error -> {

                }
            }
        }
    }
}