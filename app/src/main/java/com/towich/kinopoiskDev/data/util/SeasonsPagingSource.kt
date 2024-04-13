package com.towich.kinopoiskDev.data.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.towich.kinopoiskDev.data.model.SeasonModel
import com.towich.kinopoiskDev.data.network.ApiService
import com.towich.kinopoiskDev.data.source.SessionStorage

class SeasonsPagingSource(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage
) : PagingSource<Int, SeasonModel>() {
    override fun getRefreshKey(state: PagingState<Int, SeasonModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SeasonModel> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getSeasonsByMovieId(
                page = page,
                movieId = listOf(sessionStorage.currentMovie?.id.toString())
            )

            LoadResult.Page(
                data = response.body()!!.docs.map { it.convertToSeasonModel() },
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.body()!!.docs.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}