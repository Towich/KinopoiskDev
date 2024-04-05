package com.towich.kinopoiskDev

import android.app.Application
import com.towich.kinopoiskDev.di.component.AppComponent
import com.towich.kinopoiskDev.di.component.DaggerAppComponent


class App : Application(){
    val appComponent = DaggerAppComponent.create()
}