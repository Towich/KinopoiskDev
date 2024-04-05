package com.towich.kinopoiskDev.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.towich.kinopoiskDev.ui.screen.main.MainScreen
import com.towich.kinopoiskDev.ui.screen.product.MovieScreen

@Composable
fun Navigation(
    navController: NavHostController,
    context: Context
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController, context = context)
        }
        composable(route = Screen.MovieScreen.route) {
            MovieScreen(navController = navController)
        }
    }
}