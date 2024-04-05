package com.towich.kinopoiskDev.di.module

import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.data.repository.MainRepositoryImpl
import com.towich.kinopoiskDev.data.source.SessionStorage
import com.towich.kinopoiskDev.di.scope.AppScope
import com.towich.kinopoiskDev.domain.GetMoviesPageUseCase
import com.towich.kinopoiskDev.domain.GetMoviesUseCase
import com.towich.kinopoiskDev.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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