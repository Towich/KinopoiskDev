package com.towich.kinopoiskDev.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.towich.kinopoiskDev.data.room.entity.GenreEntity

@Dao
interface GenreDao {
    @Query("SELECT * FROM genres_table")
    suspend fun getAllGenres(): List<GenreEntity>

    @Query("SELECT COUNT(*) FROM genres_table")
    suspend fun getGenresCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenre(genre: GenreEntity)
}