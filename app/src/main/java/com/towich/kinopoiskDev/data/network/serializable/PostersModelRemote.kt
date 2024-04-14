package com.towich.kinopoiskDev.data.network.serializable

import com.towich.kinopoiskDev.data.model.PosterModel
import com.towich.kinopoiskDev.data.model.SeasonModel

data class PosterModelRemote(
    val url: String?,
    val previewUrl: String?
) {
    fun convertToPosterModel() = PosterModel(
        url = url,
        previewUrl = previewUrl
    )
}

data class PosterModelResponseRemote(
    val docs: List<PosterModelRemote>
)