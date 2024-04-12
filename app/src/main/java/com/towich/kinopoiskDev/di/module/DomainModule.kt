package com.towich.kinopoiskDev.di.module

import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.di.scope.AppScope
import com.towich.kinopoiskDev.domain.GetMoviesPageUseCase
import com.towich.kinopoiskDev.domain.GetMoviesUseCase
import dagger.Module
import dagger.Provides

@Module
object DomainModule {

    @Provides
    @AppScope
    fun provideGetMoviesUseCase(repository: MainRepository): GetMoviesUseCase {
        return GetMoviesUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetMoviesPageUseCase(repository: MainRepository): GetMoviesPageUseCase {
        return GetMoviesPageUseCase(repository = repository)
    }
}