package com.towich.kinopoiskDev

import com.towich.kinopoiskDev.data.network.ApiResult
import com.towich.kinopoiskDev.data.network.MockApiService
import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.data.repository.MainRepositoryImpl
import com.towich.kinopoiskDev.data.source.SessionStorage
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainRepositoryUnitTest {

    private lateinit var sessionStorage: SessionStorage
    private lateinit var repository: MainRepository

    @Before
    fun setup(){
        sessionStorage = SessionStorage()
        repository = MainRepositoryImpl(apiService = MockApiService(), sessionStorage = sessionStorage)
    }

    @Test
    fun `get movies success`(){
        runBlocking {
            val result = repository.getMovies()

            Assert.assertEquals("Один дома", (result as ApiResult.Success).data.docs[0].name)
        }
    }

    @Test
    fun `get genres first launch success`(){
        runBlocking {
            val result = repository.getGenres()

            Assert.assertEquals("аниме", (result as ApiResult.Success).data[2].name)
        }
    }

    @Test
    fun `genres in sessionStorage not null after first launch success`(){
        runBlocking {
            repository.getGenres()

            Assert.assertEquals("аниме", sessionStorage.listOfGenres!![2].name)
        }
    }

    @Test
    fun `get countries first launch success`(){
        runBlocking {
            val result = repository.getCountries()

            Assert.assertEquals("Австрия", (result as ApiResult.Success).data[1].name)
        }
    }

    @Test
    fun `countries in sessionStorage not null after first launch success`(){
        runBlocking {
            repository.getCountries()

            Assert.assertEquals("Австрия", sessionStorage.listOfCountries!![1].name)
        }
    }
}