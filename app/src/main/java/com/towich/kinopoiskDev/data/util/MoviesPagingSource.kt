package com.towich.kinopoiskDev.data.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.network.ApiService
import com.towich.kinopoiskDev.data.source.SessionStorage
import retrofit2.HttpException

class MoviesPagingSource(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage,
    private val query: String? = null,
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
            val response = if (query == null) {
                apiService.getMovies(
                    page = page,
                    genre = sessionStorage.listOfFilters[0],
                    country = sessionStorage.listOfFilters[1],
                    year = sessionStorage.listOfFilters[2],
                    age = sessionStorage.listOfFilters[3],
                )
            }
            else {
                apiService.searchMovie(
                    page = page,
                    query = query
                )
            }


            return LoadResult.Page(
                data = response.body()!!.docs.map { it.convertToMovieModel() },
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.body()!!.docs.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}