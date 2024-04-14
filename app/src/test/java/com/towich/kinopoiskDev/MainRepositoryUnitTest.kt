package com.towich.kinopoiskDev

import android.content.Context
import android.net.ConnectivityManager
import com.towich.kinopoiskDev.data.network.ApiResult
import com.towich.kinopoiskDev.data.network.MockApiService
import com.towich.kinopoiskDev.data.repository.MainRepository
import com.towich.kinopoiskDev.data.repository.MainRepositoryImpl
import com.towich.kinopoiskDev.data.room.dao.CountryDao
import com.towich.kinopoiskDev.data.room.dao.GenreDao
import com.towich.kinopoiskDev.data.room.dao.MovieDao
import com.towich.kinopoiskDev.data.room.dao.QueryDao
import com.towich.kinopoiskDev.data.source.SessionStorage
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class MainRepositoryUnitTest {

    private lateinit var sessionStorage: SessionStorage
    private lateinit var repository: MainRepository
    private lateinit var movieDao: MovieDao
    private lateinit var genreDao: GenreDao
    private lateinit var countryDao: CountryDao
    private lateinit var queryDao: QueryDao
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var context: Context

    @Before
    fun setup() {
        val mockMovieDao = mock(MovieDao::class.java)
        val mockGenreDao = mock(GenreDao::class.java)
        val mockCountryDao = mock(CountryDao::class.java)
        val mockQueryDao = mock(QueryDao::class.java)
        val mockConnectivityManager = mock(ConnectivityManager::class.java)
        val mockContext = mock(Context::class.java)

        movieDao = mockMovieDao
        genreDao = mockGenreDao
        countryDao = mockCountryDao
        queryDao = mockQueryDao
        connectivityManager = mockConnectivityManager
        context = mockContext

        sessionStorage = SessionStorage()
        repository = MainRepositoryImpl(
            apiService = MockApiService(),
            sessionStorage = sessionStorage,
            movieDao = movieDao,
            genreDao = genreDao,
            countryDao = countryDao,
            queryDao = queryDao,
            connectivityManager = connectivityManager,
            context = context
        )
    }

    @Test
    fun `get movies success`() {
        runBlocking {
            val result = repository.getMovies()

            Assert.assertEquals("Один дома", (result as ApiResult.Success).data.docs[0].name)
        }
    }

    @Test
    fun `get genres first launch success`() {
        runBlocking {
            val result = repository.getGenres()

            Assert.assertEquals("аниме", (result as ApiResult.Success).data[2].name)
        }
    }

    @Test
    fun `genres in sessionStorage not null after first launch success`() {
        runBlocking {
            repository.getGenres()

            Assert.assertEquals("аниме", sessionStorage.listOfGenres!![2].name)
        }
    }

    @Test
    fun `get countries first launch success`() {
        runBlocking {
            val result = repository.getCountries()

            Assert.assertEquals("Австрия", (result as ApiResult.Success).data[1].name)
        }
    }

    @Test
    fun `countries in sessionStorage not null after first launch success`() {
        runBlocking {
            repository.getCountries()

            Assert.assertEquals("Австрия", sessionStorage.listOfCountries!![1].name)
        }
    }
}