package com.towich.kinopoiskDev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.towich.kinopoiskDev.navigation.Navigation
import com.towich.kinopoiskDev.ui.theme.VkTestTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            VkTestTheme {
                Navigation(
                    navController = navController,
                    context = applicationContext
                )
            }
        }
    }
}