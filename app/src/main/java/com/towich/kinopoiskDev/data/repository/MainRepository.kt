package com.towich.kinopoiskDev.data.repository

import androidx.paging.PagingData
import com.towich.kinopoiskDev.data.model.ActorModel
import com.towich.kinopoiskDev.data.model.EpisodeModel
import com.towich.kinopoiskDev.data.model.FieldModel
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.model.ReviewModel
import com.towich.kinopoiskDev.data.model.SeasonModel
import com.towich.kinopoiskDev.data.network.serializable.MovieModelResponseRemote
import com.towich.kinopoiskDev.data.network.ApiResult
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getMovies(): ApiResult<MovieModelResponseRemote>
    fun getMoviesPage(): Flow<PagingData<MovieModel>>

    suspend fun getGenres(): ApiResult<List<FieldModel>>
    suspend fun getCountries(): ApiResult<List<FieldModel>>

    fun setFilters(listOfFilters: List<String?>)
    fun getFilters(): List<String?>

    fun setCurrentMovie(movie: MovieModel?)
    fun getCurrentMovie(): MovieModel?

    fun getActorsPage(): Flow<PagingData<ActorModel>>
    fun getSeasonsPage(): Flow<PagingData<SeasonModel>>

    fun setCurrentSeason(seasonNumber: Int?)
    fun getCurrentSeason(): Int?

    fun getEpisodesPage(): Flow<PagingData<EpisodeModel>>

    fun getReviewPage(): Flow<PagingData<ReviewModel>>
}