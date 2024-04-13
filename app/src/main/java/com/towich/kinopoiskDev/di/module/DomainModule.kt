package com.towich.kinopoiskDev.di.module

import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.di.scope.AppScope
import com.towich.kinopoiskDev.domain.GetCountriesUseCase
import com.towich.kinopoiskDev.domain.GetFiltersUseCase
import com.towich.kinopoiskDev.domain.GetGenresUseCase
import com.towich.kinopoiskDev.domain.GetMoviesPageUseCase
import com.towich.kinopoiskDev.domain.GetMoviesUseCase
import com.towich.kinopoiskDev.domain.SetFiltersUseCase
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
    @Provides
    @AppScope
    fun provideGetGenresUseCase(repository: MainRepository): GetGenresUseCase {
        return GetGenresUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetCountriesUseCase(repository: MainRepository): GetCountriesUseCase {
        return GetCountriesUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideSetFiltersUseCase(repository: MainRepository): SetFiltersUseCase {
        return SetFiltersUseCase(repository = repository)
    }
    @Provides
    @AppScope
    fun provideGetFiltersUseCase(repository: MainRepository): GetFiltersUseCase {
        return GetFiltersUseCase(repository = repository)
    }
}