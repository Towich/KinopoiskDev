package com.towich.kinopoiskDev.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.towich.kinopoiskDev.data.room.entity.CountryEntity

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries_table")
    suspend fun getAllCountries(): List<CountryEntity>

    @Query("SELECT COUNT(*) FROM countries_table")
    suspend fun getCountriesCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCountry(country: CountryEntity)
}