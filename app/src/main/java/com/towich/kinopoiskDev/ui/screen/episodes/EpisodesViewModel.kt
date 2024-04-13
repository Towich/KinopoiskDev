package com.towich.kinopoiskDev.ui.screen.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.towich.kinopoiskDev.data.model.EpisodeModel
import com.towich.kinopoiskDev.domain.GetCurrentSeasonUseCase
import com.towich.kinopoiskDev.domain.GetEpisodesPageUseCase
import kotlinx.coroutines.flow.Flow

class EpisodesViewModel(
    private val getEpisodesPage: GetEpisodesPageUseCase,
    private val getCurrentSeason: GetCurrentSeasonUseCase,
) : ViewModel() {

    fun performGetEpisodes(): Flow<PagingData<EpisodeModel>> = getEpisodesPage().cachedIn(viewModelScope)

    fun performGetCurrentSeason(): Int? {
        return getCurrentSeason()
    }
}