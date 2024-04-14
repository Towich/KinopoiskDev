package com.towich.kinopoiskDev.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.towich.kinopoiskDev.data.room.entity.MovieEntity

@Dao
interface MovieFavouriteDao {
    @Query("SELECT * FROM movies_favourite_table LIMIT :limit OFFSET :offset")
    suspend fun getFavoriteMovies(limit: Int, offset: Int): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieFavourite(movie: MovieEntity)

    @Query("DELETE FROM movies_favourite_table WHERE id = :movieId")
    suspend fun deleteMovieFavouriteById(movieId: Int)
}