package com.towich.kinopoiskDev.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.towich.kinopoiskDev.data.room.dao.CountryDao
import com.towich.kinopoiskDev.data.room.dao.GenreDao
import com.towich.kinopoiskDev.data.room.dao.MovieDao
import com.towich.kinopoiskDev.data.room.dao.MovieFavouriteDao
import com.towich.kinopoiskDev.data.room.dao.QueryDao
import com.towich.kinopoiskDev.data.room.entity.CountryEntity
import com.towich.kinopoiskDev.data.room.entity.GenreEntity
import com.towich.kinopoiskDev.data.room.entity.MovieEntity
import com.towich.kinopoiskDev.data.room.entity.MovieFavouriteEntity
import com.towich.kinopoiskDev.data.room.entity.QueryEntity
import com.towich.kinopoiskDev.data.util.Converters

@Database(entities = [MovieEntity::class, MovieFavouriteEntity::class, GenreEntity::class, CountryEntity::class, QueryEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
    abstract fun countryDao(): CountryDao
    abstract fun movieFavouriteDao(): MovieFavouriteDao
    abstract fun queryDao(): QueryDao

    companion object {
        private var dbInstance: MainDatabase? = null

        fun getDatabaseInstance(context: Context): MainDatabase {
            synchronized(this) {
                if (dbInstance == null) {
                    dbInstance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java,
                        "main_db"
                    ).build()
                }
                return dbInstance!!
            }
        }
    }
}