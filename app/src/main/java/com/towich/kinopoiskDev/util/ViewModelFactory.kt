package com.towich.kinopoiskDev.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.towich.kinopoiskDev.domain.GetActorsPageUseCase
import com.towich.kinopoiskDev.domain.GetCountriesUseCase
import com.towich.kinopoiskDev.domain.GetCurrentMovieUseCase
import com.towich.kinopoiskDev.domain.GetCurrentSeasonUseCase
import com.towich.kinopoiskDev.domain.GetEpisodesPageUseCase
import com.towich.kinopoiskDev.domain.GetFiltersUseCase
import com.towich.kinopoiskDev.domain.GetGenresUseCase
import com.towich.kinopoiskDev.domain.GetMoviesPageUseCase
import com.towich.kinopoiskDev.domain.GetMoviesUseCase
import com.towich.kinopoiskDev.domain.GetReviewsPageUseCase
import com.towich.kinopoiskDev.domain.GetSeasonsPageUseCase
import com.towich.kinopoiskDev.domain.SetCurrentMovieUseCase
import com.towich.kinopoiskDev.domain.SetCurrentSeasonUseCase
import com.towich.kinopoiskDev.domain.SetFiltersUseCase
import com.towich.kinopoiskDev.ui.screen.allmovies.AllMoviesViewModel
import com.towich.kinopoiskDev.ui.screen.episodes.EpisodesViewModel
import com.towich.kinopoiskDev.ui.screen.filters.FiltersViewModel
import com.towich.kinopoiskDev.ui.screen.main.MainViewModel
import com.towich.kinopoiskDev.ui.screen.movie.MovieViewModel
import com.towich.kinopoiskDev.ui.screen.reviews.ReviewsViewModel

class ViewModelFactory(
    private val getMovies: GetMoviesUseCase,
    private val getMoviesPage: GetMoviesPageUseCase,
    private val getGenres: GetGenresUseCase,
    private val getCountries: GetCountriesUseCase,
    private val setFilters: SetFiltersUseCase,
    private val getFilters: GetFiltersUseCase,
    private val getCurrentMovie: GetCurrentMovieUseCase,
    private val setCurrentMovie: SetCurrentMovieUseCase,
    private val getActorsPage: GetActorsPageUseCase,
    private val getSeasonsPage: GetSeasonsPageUseCase,
    private val setCurrentSeason: SetCurrentSeasonUseCase,
    private val getCurrentSeason: GetCurrentSeasonUseCase,
    private val getEpisodesPage: GetEpisodesPageUseCase,
    private val getReviewsPage: GetReviewsPageUseCase,
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(
                    getMovies = getMovies,
                    setCurrentMovie = setCurrentMovie
                ) as T
            }

            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(
                    getCurrentMovie = getCurrentMovie,
                    getActorsPage = getActorsPage,
                    getSeasonsPage = getSeasonsPage,
                    setCurrentSeason = setCurrentSeason
                ) as T
            }

            modelClass.isAssignableFrom(AllMoviesViewModel::class.java) -> {
                AllMoviesViewModel(
                    getMovies = getMovies,
                    getMoviesPage = getMoviesPage,
                    setCurrentMovie = setCurrentMovie
                ) as T
            }

            modelClass.isAssignableFrom(FiltersViewModel::class.java) -> {
                FiltersViewModel(
                    getGenres = getGenres,
                    getCountries = getCountries,
                    setFilters = setFilters,
                    getFilters = getFilters
                ) as T
            }

            modelClass.isAssignableFrom(EpisodesViewModel::class.java) -> {
                EpisodesViewModel(
                    getEpisodesPage = getEpisodesPage,
                    getCurrentSeason = getCurrentSeason
                ) as T
            }

            modelClass.isAssignableFrom(ReviewsViewModel::class.java) -> {
                ReviewsViewModel(
                    getReviewsPage = getReviewsPage
                ) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}