package com.towich.kinopoiskDev.di.module

import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.data.repository.MainRepositoryImpl
import com.towich.kinopoiskDev.data.source.SessionStorage
import com.towich.kinopoiskDev.di.scope.AppScope
import com.towich.kinopoiskDev.data.network.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @AppScope
    fun provideMainRepository(
        apiService: ApiService,
        sessionStorage: SessionStorage
    ): MainRepository {
        return MainRepositoryImpl(
            apiService = apiService,
            sessionStorage = sessionStorage
        )
    }

    @Provides
    @AppScope
    fun provideSessionStorage(): SessionStorage {
        return SessionStorage()
    }
}