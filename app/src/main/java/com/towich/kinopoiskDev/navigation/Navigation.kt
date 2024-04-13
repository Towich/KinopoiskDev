package com.towich.kinopoiskDev.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.towich.kinopoiskDev.ui.screen.allmovies.AllMoviesScreen
import com.towich.kinopoiskDev.ui.screen.allmovies.AllMoviesViewModel
import com.towich.kinopoiskDev.ui.screen.episodes.EpisodesScreen
import com.towich.kinopoiskDev.ui.screen.episodes.EpisodesViewModel
import com.towich.kinopoiskDev.ui.screen.filters.FiltersScreen
import com.towich.kinopoiskDev.ui.screen.filters.FiltersViewModel
import com.towich.kinopoiskDev.ui.screen.main.MainScreen
import com.towich.kinopoiskDev.ui.screen.main.MainViewModel
import com.towich.kinopoiskDev.ui.screen.movie.MovieScreen
import com.towich.kinopoiskDev.ui.screen.movie.MovieViewModel
import com.towich.kinopoiskDev.ui.screen.reviews.ReviewsScreen
import com.towich.kinopoiskDev.ui.screen.reviews.ReviewsViewModel
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
                onNavClickSingleMovie = {
                    navController.navigate(Screen.MovieScreen.route)
                },
                onNavClickAllMovies = {
                    navController.navigate(Screen.AllMoviesScreen.route)
                }
            )
        }
        composable(route = Screen.MovieScreen.route) {

            val movieViewModel: MovieViewModel =
                ViewModelProvider(it, viewModelFactory)[MovieViewModel::class.java]

            MovieScreen(
                viewModel = movieViewModel,
                onSeasonClicked = {
                    navController.navigate(Screen.EpisodesScreen.route)
                },
                onNavIconClicked = {
                    navController.popBackStack()
                },
                onReviewsButtonNavClicked = {
                    navController.navigate(Screen.ReviewsScreen.route)
                }
            )
        }
        composable(route = Screen.AllMoviesScreen.route) {

            val allMoviesViewModel: AllMoviesViewModel =
                ViewModelProvider(it, viewModelFactory)[AllMoviesViewModel::class.java]

            AllMoviesScreen(
                viewModel = allMoviesViewModel,
                onMovieClicked = {
                    navController.navigate(Screen.MovieScreen.route)
                },
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

        composable(route = Screen.EpisodesScreen.route) {

            val episodesViewModel: EpisodesViewModel =
                ViewModelProvider(it, viewModelFactory)[EpisodesViewModel::class.java]

            EpisodesScreen(
                viewModel = episodesViewModel,
                onNavIconClicked = {
                    navController.popBackStack()
                }
            )
        }
        composable(route = Screen.ReviewsScreen.route) {

            val reviewsViewModel: ReviewsViewModel =
                ViewModelProvider(it, viewModelFactory)[ReviewsViewModel::class.java]

            ReviewsScreen(
                viewModel = reviewsViewModel,
                onNavIconClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}