package com.towich.kinopoiskDev.data.repository

import com.towich.kinopoiskDev.data.source.SessionStorage
import com.towich.kinopoiskDev.network.ApiService

class MainRepositoryImpl(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage
) : MainRepository {

}