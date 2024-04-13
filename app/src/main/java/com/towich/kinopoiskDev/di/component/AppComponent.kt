package com.towich.kinopoiskDev.di.component

import android.content.Context
import com.towich.kinopoiskDev.di.module.AppModule
import com.towich.kinopoiskDev.di.module.DomainModule
import com.towich.kinopoiskDev.di.module.NetworkModule
import com.towich.kinopoiskDev.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component


@AppScope
@Component(modules = [AppModule::class, NetworkModule::class, DomainModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun activityComponent(): ActivityComponent.Factory
}