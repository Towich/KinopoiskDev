package com.towich.kinopoiskDev.di.component

import com.towich.kinopoiskDev.MainActivity
import com.towich.kinopoiskDev.di.module.ViewModelModule
import com.towich.kinopoiskDev.di.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = ([ViewModelModule::class]))
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(activity: MainActivity)
}