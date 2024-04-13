package com.towich.kinopoiskDev.ui.screen.filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.towich.kinopoiskDev.data.model.FieldModel
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.network.ApiResult
import com.towich.kinopoiskDev.domain.GetCountriesUseCase
import com.towich.kinopoiskDev.domain.GetFiltersUseCase
import com.towich.kinopoiskDev.domain.GetGenresUseCase
import com.towich.kinopoiskDev.domain.SetFiltersUseCase
import com.towich.kinopoiskDev.ui.util.ScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FiltersViewModel(
    private val getGenres: GetGenresUseCase,
    private val getCountries: GetCountriesUseCase,
    private val setFilters: SetFiltersUseCase,
    private val getFilters: GetFiltersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScreenUiState>(ScreenUiState.Initial)
    val uiState: StateFlow<ScreenUiState> = _uiState

    private val _genres = MutableStateFlow<List<FieldModel>>(listOf())
    val genres: StateFlow<List<FieldModel>> = _genres

    private val _countries = MutableStateFlow<List<FieldModel>>(listOf())
    val countries: StateFlow<List<FieldModel>> = _countries

    init {
        performGetGenres()
        performGetCountries()
    }

    private fun performGetGenres(){
        viewModelScope.launch {
            _uiState.value = ScreenUiState.Loading

            when (val result = getGenres()) {
                is ApiResult.Success -> {
                    _genres.value = result.data
                    _uiState.value = ScreenUiState.Success(result.data)
                }

                is ApiResult.Error -> {
                    _uiState.value = ScreenUiState.Error(result.error)
                }
            }
        }
    }

    private fun performGetCountries(){
        viewModelScope.launch {
            _uiState.value = ScreenUiState.Loading

            when (val result = getCountries()) {
                is ApiResult.Success -> {
                    _countries.value = result.data
                    _uiState.value = ScreenUiState.Success(result.data)
                }

                is ApiResult.Error -> {
                    _uiState.value = ScreenUiState.Error(result.error)
                }
            }
        }
    }

    fun applyFilters(listOfFilters: List<String?>){
        setFilters(listOfFilters = listOfFilters)
    }

    fun performGetFilters(): List<String?> {
        return getFilters()
    }
}