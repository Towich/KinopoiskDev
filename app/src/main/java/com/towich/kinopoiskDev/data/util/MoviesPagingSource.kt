package com.towich.kinopoiskDev.data.util

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.towich.kinopoiskDev.R
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.network.ApiService
import com.towich.kinopoiskDev.data.room.dao.MovieDao
import com.towich.kinopoiskDev.data.source.Constants
import com.towich.kinopoiskDev.data.source.SessionStorage

class MoviesPagingSource(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage,
    private val query: String? = null,
    private val movieDao: MovieDao,
    private val connectivityManager: ConnectivityManager,
    private val context: Context
) : PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        val page = params.key ?: 1
        try {
            if (isInternetAvailable()) {
                val response = if (query == null) {
                    apiService.getMovies(
                        page = page,
                        genre = sessionStorage.listOfFilters[0],
                        country = sessionStorage.listOfFilters[1],
                        year = sessionStorage.listOfFilters[2],
                        age = sessionStorage.listOfFilters[3],
                        isSeries = sessionStorage.listOfFilters[4].toBoolean(),
                    )
                }
                else {
                    apiService.searchMovie(
                        page = page,
                        query = query
                    )
                }

                if (movieDao.getMoviesCount() > 40) movieDao.deleteAllMovies()

                response.body()?.docs?.forEach { movie ->
                    movieDao.insertMovie(movie.convertToMovieModel().converterToMovieEntity())
                }

                return LoadResult.Page(
                    data = response.body()!!.docs.map { it.convertToMovieModel() },
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (response.body()!!.docs.isEmpty()) null else page.plus(1),
                )
            } else {
                Toast.makeText(context,
                    context.getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show()
                val movies = movieDao.getAllMovies(Constants.pageLimit, Constants.pageLimit * (page - 1)).map { it.convertToMovieModel() }
                return LoadResult.Page(
                    data = movies,
                    prevKey = if (page == 1) null else page.minus(1),
                    nextKey = if (movies.isEmpty()) null else page.plus(1)
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private fun isInternetAvailable(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}