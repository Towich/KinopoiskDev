package com.towich.kinopoiskDev.data.repository

import androidx.paging.PagingData
import com.towich.kinopoiskDev.data.model.FieldModel
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.network.serializable.MovieModelResponseRemote
import com.towich.kinopoiskDev.data.network.ApiResult
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getMovies(): ApiResult<MovieModelResponseRemote>
    fun getMoviesPage(): Flow<PagingData<MovieModel>>

    suspend fun getGenres(): ApiResult<List<FieldModel>>
    suspend fun getCountries(): ApiResult<List<FieldModel>>
    fun setFilters(listOfFilters: List<String?>)
}