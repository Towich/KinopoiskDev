package com.towich.kinopoiskDev.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.towich.kinopoiskDev.data.room.entity.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies_table LIMIT :limit OFFSET :offset")
    suspend fun getAllMovies(limit: Int, offset: Int): List<MovieEntity>

    @Query("SELECT COUNT(*) FROM movies_table")
    suspend fun getMoviesCount(): Int

    @Query("SELECT * FROM movies_table WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity?

    @Query("SELECT * FROM movies_table WHERE name LIKE '%' || :name || '%'")
    suspend fun searchMoviesByName(name: String): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieEntity)

    @Query("DELETE FROM movies_table")
    suspend fun deleteAllMovies()
}