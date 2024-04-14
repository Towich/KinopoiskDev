package com.towich.kinopoiskDev.di.module

import android.content.Context
import android.net.ConnectivityManager
import com.towich.kinopoiskDev.data.network.ApiService
import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.data.repository.MainRepositoryImpl
import com.towich.kinopoiskDev.data.room.dao.CountryDao
import com.towich.kinopoiskDev.data.room.dao.GenreDao
import com.towich.kinopoiskDev.data.room.dao.MovieDao
import com.towich.kinopoiskDev.data.room.dao.QueryDao
import com.towich.kinopoiskDev.data.sharedpref.SharedPreferences
import com.towich.kinopoiskDev.data.source.SessionStorage
import com.towich.kinopoiskDev.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
object AppModule {
    @Provides
    @AppScope
    fun provideMainRepository(
        apiService: ApiService,
        sessionStorage: SessionStorage,
        movieDao: MovieDao,
        genreDao: GenreDao,
        countryDao: CountryDao,
        queryDao: QueryDao,
        connectivityManager: ConnectivityManager,
        context: Context
    ): MainRepository {
        return MainRepositoryImpl(
            apiService = apiService,
            sessionStorage = sessionStorage,
            movieDao = movieDao,
            genreDao = genreDao,
            countryDao = countryDao,
            queryDao = queryDao,
            connectivityManager = connectivityManager,
            context = context
        )
    }

    @Provides
    @AppScope
    fun provideSessionStorage(): SessionStorage {
        return SessionStorage()
    }

    @Provides
    @AppScope
    fun provideConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @AppScope
    fun provideSharedPref(context: Context): SharedPreferences {
        return SharedPreferences(context)
    }
}