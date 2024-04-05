package com.towich.kinopoiskDev.ui.util


sealed interface ScreenUiState {
    data object Initial : ScreenUiState
    data object Loading : ScreenUiState
    data class Success<out T>(val data: T) : ScreenUiState
    data class Error(val message: String) : ScreenUiState
}