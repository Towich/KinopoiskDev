package com.towich.kinopoiskDev.data.network.serializable

import com.towich.kinopoiskDev.data.model.EpisodeModel
import com.towich.kinopoiskDev.data.model.PosterModel

data class EpisodeModelRemote(
    val number: Int?,
    val name: String?,
    val still: PosterModel?,
    val duration: Int?,
    val description: String?
) {
    fun convertToEpisodeModel() = EpisodeModel(
        number = number,
        name = name,
        posterPreview = still?.previewUrl,
        duration = duration,
        description = description
    )
}

data class EpisodeModelResponseList(
    val episodes: List<EpisodeModelRemote>
)

data class EpisodeModelResponseRemote(
    val docs: List<EpisodeModelResponseList>
)