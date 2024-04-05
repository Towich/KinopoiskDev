package com.towich.kinopoiskDev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.towich.kinopoiskDev.di.component.ActivityComponent
import com.towich.kinopoiskDev.navigation.Navigation
import com.towich.kinopoiskDev.ui.theme.VkTestTheme
import com.towich.kinopoiskDev.util.ViewModelFactory
import javax.inject.Inject


class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent = (applicationContext as App).appComponent.activityComponent().create()
        activityComponent.inject(this)

        setContent {
            val navController = rememberNavController()

            VkTestTheme {
                Navigation(
                    navController = navController,
                    context = applicationContext,
                    viewModelFactory = viewModelFactory
                )
            }
        }
    }
}