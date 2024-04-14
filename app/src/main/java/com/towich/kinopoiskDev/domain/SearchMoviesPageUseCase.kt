package com.towich.kinopoiskDev.domain

import androidx.paging.PagingData
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class SearchMoviesPageUseCase(private val repository: MainRepository) {
    operator fun invoke(query: String): Flow<PagingData<MovieModel>> {
        return repository.searchMovieByName(query = query)
    }
}