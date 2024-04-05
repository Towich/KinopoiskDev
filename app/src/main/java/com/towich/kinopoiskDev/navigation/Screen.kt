package com.towich.kinopoiskDev.navigation

sealed class Screen(val route: String) {
    data object MainScreen : Screen(route = "main_screen")
    data object MovieScreen : Screen(route = "movie_screen")
}
