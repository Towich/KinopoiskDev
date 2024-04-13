package com.towich.kinopoiskDev.data.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.towich.kinopoiskDev.data.model.ReviewModel
import com.towich.kinopoiskDev.data.network.ApiService
import com.towich.kinopoiskDev.data.source.SessionStorage

class ReviewsPagingSource(
    private val apiService: ApiService,
    private val sessionStorage: SessionStorage
) : PagingSource<Int, ReviewModel>() {
    override fun getRefreshKey(state: PagingState<Int, ReviewModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReviewModel> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getReviews(
                page = page,
                movieId = listOf(sessionStorage.currentMovie?.id.toString())
            )

            LoadResult.Page(
                data = response.body()!!.docs.map { it.convertToReviewModel() },
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.body()!!.docs.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}