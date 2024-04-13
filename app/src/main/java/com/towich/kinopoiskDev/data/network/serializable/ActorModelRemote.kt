package com.towich.kinopoiskDev.data.network.serializable

import com.towich.kinopoiskDev.data.model.ActorModel
import com.towich.kinopoiskDev.data.model.GenreModel
import com.towich.kinopoiskDev.data.model.MovieModel
import com.towich.kinopoiskDev.data.model.PosterModel
import com.towich.kinopoiskDev.data.model.RatingModel
import com.towich.kinopoiskDev.data.model.VotesModel

data class ActorModelRemote(
    val id: Int,
    val name: String?,
    val photo: String?
) {
    fun convertToActorModel() = ActorModel(
        id = id,
        name = name,
        photo = photo
    )
}

data class ActorModelResponseRemote(
    val docs: List<ActorModelRemote>
)