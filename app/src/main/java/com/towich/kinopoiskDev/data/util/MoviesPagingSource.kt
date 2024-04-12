package com.towich.kinopoiskDev.data.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.network.ApiService

class MoviesPagingSource(
    private val apiService: ApiService,
): PagingSource<Int, MovieModel>() {
    override fun getRefreshKey(state: PagingState<Int, MovieModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieModel> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getMovies(page = page)

            LoadResult.Page(
                data = response.body()!!.docs.map { it.convertToMovieModel() },
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.body()!!.docs.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}