package com.towich.kinopoiskDev.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.towich.kinopoiskDev.domain.GetCountriesUseCase
import com.towich.kinopoiskDev.domain.GetGenresUseCase
import com.towich.kinopoiskDev.domain.GetMoviesPageUseCase
import com.towich.kinopoiskDev.domain.GetMoviesUseCase
import com.towich.kinopoiskDev.domain.SetFiltersUseCase
import com.towich.kinopoiskDev.ui.screen.allmovies.AllMoviesViewModel
import com.towich.kinopoiskDev.ui.screen.filters.FiltersViewModel
import com.towich.kinopoiskDev.ui.screen.main.MainViewModel
import com.towich.kinopoiskDev.ui.screen.movie.MovieViewModel

class ViewModelFactory(
    private val getMovies: GetMoviesUseCase,
    private val getMoviesPage: GetMoviesPageUseCase,
    private val getGenres: GetGenresUseCase,
    private val getCountries: GetCountriesUseCase,
    private val setFilters: SetFiltersUseCase,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(
                    getMovies = getMovies
                ) as T
            }

            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(

                ) as T
            }

            modelClass.isAssignableFrom(AllMoviesViewModel::class.java) -> {
                AllMoviesViewModel(
                    getMovies = getMovies,
                    getMoviesPage = getMoviesPage
                ) as T
            }

            modelClass.isAssignableFrom(FiltersViewModel::class.java) -> {
                FiltersViewModel(
                    getGenres = getGenres,
                    getCountries = getCountries,
                    setFilters = setFilters
                ) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}