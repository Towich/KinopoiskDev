package com.towich.kinopoiskDev.ui.screen.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.towich.kinopoiskDev.data.model.ActorModel
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.model.SeasonModel
import com.towich.kinopoiskDev.domain.GetActorsPageUseCase
import com.towich.kinopoiskDev.domain.GetCurrentMovieUseCase
import com.towich.kinopoiskDev.domain.GetSeasonsPageUseCase
import com.towich.kinopoiskDev.domain.SetCurrentSeasonUseCase
import kotlinx.coroutines.flow.Flow

class MovieViewModel(
    private val getCurrentMovie: GetCurrentMovieUseCase,
    private val getActorsPage: GetActorsPageUseCase,
    private val getSeasonsPage: GetSeasonsPageUseCase,
    private val setCurrentSeason: SetCurrentSeasonUseCase,
) : ViewModel() {

    fun performGetActors(): Flow<PagingData<ActorModel>> = getActorsPage().cachedIn(viewModelScope)
    fun performGetSeasons(): Flow<PagingData<SeasonModel>> = getSeasonsPage().cachedIn(viewModelScope)

    fun performGetCurrentMovie(): MovieModel? {
        return getCurrentMovie()
    }

    fun performSetCurrentSeason(seasonNumber: Int?){
        setCurrentSeason(seasonNumber = seasonNumber)
    }
}