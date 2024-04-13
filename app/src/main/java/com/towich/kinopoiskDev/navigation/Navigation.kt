package com.towich.kinopoiskDev.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.towich.kinopoiskDev.ui.screen.allmovies.AllMoviesScreen
import com.towich.kinopoiskDev.ui.screen.allmovies.AllMoviesViewModel
import com.towich.kinopoiskDev.ui.screen.filters.FiltersScreen
import com.towich.kinopoiskDev.ui.screen.filters.FiltersViewModel
import com.towich.kinopoiskDev.ui.screen.main.MainScreen
import com.towich.kinopoiskDev.ui.screen.main.MainViewModel
import com.towich.kinopoiskDev.ui.screen.movie.MovieScreen
import com.towich.kinopoiskDev.util.ViewModelFactory

@Composable
fun Navigation(
    navController: NavHostController,
    appContext: Context,
    viewModelFactory: ViewModelFactory
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.MainScreen.route) {

            val mainViewModel: MainViewModel =
                ViewModelProvider(it, viewModelFactory)[MainViewModel::class.java]

            MainScreen(
                viewModel = mainViewModel,
                onClickAllMovies = {
                    navController.navigate(Screen.AllMoviesScreen.route)
                }
            )
        }
        composable(route = Screen.MovieScreen.route) {
            MovieScreen(navController = navController)
        }
        composable(route = Screen.AllMoviesScreen.route) {

            val allMoviesViewModel: AllMoviesViewModel =
                ViewModelProvider(it, viewModelFactory)[AllMoviesViewModel::class.java]

            AllMoviesScreen(
                viewModel = allMoviesViewModel,
                onFilterIconClicked = {
                    navController.navigate(Screen.FiltersScreen.route)
                },
                onNavIconClicked = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.FiltersScreen.route) {

            val filtersViewModel: FiltersViewModel =
                ViewModelProvider(it, viewModelFactory)[FiltersViewModel::class.java]

            FiltersScreen(
                appContext = appContext,
                viewModel = filtersViewModel,
                onNavIconClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}