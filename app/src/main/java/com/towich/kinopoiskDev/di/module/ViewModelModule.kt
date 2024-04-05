package com.towich.kinopoiskDev.di.module

import com.towich.kinopoiskDev.di.scope.AppScope
import com.towich.kinopoiskDev.util.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {

    @Provides
    @AppScope
    fun provideViewModelFactory(

    ): ViewModelFactory = ViewModelFactory()
}