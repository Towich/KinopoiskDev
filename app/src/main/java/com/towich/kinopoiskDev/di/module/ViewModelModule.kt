package com.towich.kinopoiskDev.di.module

import com.towich.kinopoiskDev.di.scope.ActivityScope
import com.towich.kinopoiskDev.domain.GetActorsPageUseCase
import com.towich.kinopoiskDev.domain.GetCountriesUseCase
import com.towich.kinopoiskDev.domain.GetCurrentMovieUseCase
import com.towich.kinopoiskDev.domain.GetFiltersUseCase
import com.towich.kinopoiskDev.domain.GetGenresUseCase
import com.towich.kinopoiskDev.domain.GetMoviesPageUseCase
import com.towich.kinopoiskDev.domain.GetMoviesUseCase
import com.towich.kinopoiskDev.domain.SetCurrentMovieUseCase
import com.towich.kinopoiskDev.domain.SetFiltersUseCase
import com.towich.kinopoiskDev.util.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {

    @Provides
    @ActivityScope
    fun provideViewModelFactory(
        getMovies: GetMoviesUseCase,
        getMoviesPage: GetMoviesPageUseCase,
        getGenresUse: GetGenresUseCase,
        getCountries: GetCountriesUseCase,
        setFilters: SetFiltersUseCase,
        getFilters: GetFiltersUseCase,
        getCurrentMovie: GetCurrentMovieUseCase,
        setCurrentMovie: SetCurrentMovieUseCase,
        getActorsPage: GetActorsPageUseCase,
    ): ViewModelFactory = ViewModelFactory(
        getMovies = getMovies,
        getMoviesPage = getMoviesPage,
        getGenres = getGenresUse,
        getCountries = getCountries,
        setFilters = setFilters,
        getFilters = getFilters,
        getCurrentMovie = getCurrentMovie,
        setCurrentMovie = setCurrentMovie,
        getActorsPage = getActorsPage
    )
}