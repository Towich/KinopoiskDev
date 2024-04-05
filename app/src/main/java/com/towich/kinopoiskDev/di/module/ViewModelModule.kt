package com.towich.kinopoiskDev.di.module

import com.towich.kinopoiskDev.di.scope.ActivityScope
import com.towich.kinopoiskDev.domain.GetMoviesPageUseCase
import com.towich.kinopoiskDev.domain.GetMoviesUseCase
import com.towich.kinopoiskDev.util.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {

    @Provides
    @ActivityScope
    fun provideViewModelFactory(
        getMovies: GetMoviesUseCase,
        getMoviesPage: GetMoviesPageUseCase
    ): ViewModelFactory = ViewModelFactory(
        getMovies = getMovies,
        getMoviesPage = getMoviesPage
    )
}