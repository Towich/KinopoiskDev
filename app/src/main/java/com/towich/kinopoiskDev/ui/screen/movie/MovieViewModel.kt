package com.towich.kinopoiskDev.ui.screen.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.towich.kinopoiskDev.data.model.ActorModel
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.model.PosterModel
import com.towich.kinopoiskDev.data.model.SeasonModel
import com.towich.kinopoiskDev.data.network.ApiResult
import com.towich.kinopoiskDev.domain.GetActorsPageUseCase
import com.towich.kinopoiskDev.domain.GetCurrentMovieUseCase
import com.towich.kinopoiskDev.domain.GetPostersUseCase
import com.towich.kinopoiskDev.domain.GetSeasonsPageUseCase
import com.towich.kinopoiskDev.domain.SetCurrentSeasonUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getCurrentMovie: GetCurrentMovieUseCase,
    private val getActorsPage: GetActorsPageUseCase,
    private val getSeasonsPage: GetSeasonsPageUseCase,
    private val setCurrentSeason: SetCurrentSeasonUseCase,
    private val getPosters: GetPostersUseCase,
) : ViewModel() {

    private val _posters = MutableStateFlow<List<PosterModel>>(listOf())
    val posters: StateFlow<List<PosterModel>> = _posters

    val actors = getActorsPage().cachedIn(viewModelScope)
    val seasons = getSeasonsPage().cachedIn(viewModelScope)

    fun performGetPosters(){
        viewModelScope.launch {
            when (val result = getPosters()) {
                is ApiResult.Success -> {
                    _posters.value = result.data.docs.map { it.convertToPosterModel() }
                }

                is ApiResult.Error -> {

                }
            }
        }
    }

    fun performGetCurrentMovie(): MovieModel? {
        return getCurrentMovie()
    }

    fun performSetCurrentSeason(seasonNumber: Int?){
        setCurrentSeason(seasonNumber = seasonNumber)
    }
}