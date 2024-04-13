package com.towich.kinopoiskDev.data.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.towich.kinopoiskDev.data.model.EpisodeModel
import com.towich.kinopoiskDev.data.network.ApiService
import com.towich.kinopoiskDev.data.source.SessionStorage

class EpisodesPagingSource(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage
) : PagingSource<Int, EpisodeModel>() {
    override fun getRefreshKey(state: PagingState<Int, EpisodeModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeModel> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getEpisodes(
                page = page,
                movieId = listOf(sessionStorage.currentMovie?.id.toString()),
                number = listOf(sessionStorage.currentSeason.toString())
            )

            LoadResult.Page(
                data = response.body()!!.docs.firstOrNull()?.episodes?.map { it.convertToEpisodeModel() } ?: listOf(),
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.body()!!.docs.firstOrNull() == null) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}