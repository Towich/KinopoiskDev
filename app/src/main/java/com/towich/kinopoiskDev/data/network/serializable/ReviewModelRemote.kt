package com.towich.kinopoiskDev.data.network.serializable

import com.towich.kinopoiskDev.data.model.ReviewModel

data class ReviewModelRemote(
    val id: Int,
    val title: String?,
    val type: String?,
    val review: String?,
    val author: String?
) {
    fun convertToReviewModel() = ReviewModel(
        id = id,
        title = title,
        type = type,
        review = review,
        author = author
    )
}

data class ReviewModelResponseRemote(
    val docs: List<ReviewModelRemote>
)