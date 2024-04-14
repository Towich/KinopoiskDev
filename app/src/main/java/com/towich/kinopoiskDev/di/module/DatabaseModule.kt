package com.towich.kinopoiskDev.di.module

import android.content.Context
import com.towich.kinopoiskDev.data.room.dao.CountryDao
import com.towich.kinopoiskDev.data.room.dao.GenreDao
import com.towich.kinopoiskDev.data.room.dao.MovieDao
import com.towich.kinopoiskDev.data.room.dao.MovieFavouriteDao
import com.towich.kinopoiskDev.data.room.dao.QueryDao
import com.towich.kinopoiskDev.data.room.database.MainDatabase
import com.towich.kinopoiskDev.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {
    @Provides
    @AppScope
    fun provideMainDatabase(context: Context): MainDatabase {
        return MainDatabase.getDatabaseInstance(context)
    }

    @Provides
    @AppScope
    fun provideMovieDao(mainDatabase: MainDatabase): MovieDao {
        return mainDatabase.movieDao()
    }

    @Provides
    @AppScope
    fun provideGenreDao(mainDatabase: MainDatabase): GenreDao {
        return mainDatabase.genreDao()
    }

    @Provides
    @AppScope
    fun provideCountryDao(mainDatabase: MainDatabase): CountryDao {
        return mainDatabase.countryDao()
    }

    @Provides
    @AppScope
    fun provideMovieFavouriteDao(mainDatabase: MainDatabase): MovieFavouriteDao {
        return mainDatabase.movieFavouriteDao()
    }

    @Provides
    @AppScope
    fun provideQueryDao(mainDatabase: MainDatabase): QueryDao {
        return mainDatabase.queryDao()
    }
}