package com.piavillalba.multimediadetail.data.entities

import com.google.gson.annotations.SerializedName
import com.piavillalba.core.constants.IMAGE_BASE_URL
import com.piavillalba.multimediadetail.domain.model.MultimediaDetail

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("tagline")
    val subtitle: String,
    @SerializedName("vote_average")
    val vote: Double,
    @SerializedName("poster_path")
    val image: String,
    @SerializedName("backdrop_path")
    val poster: String
) {
    fun toMultimediaDetail(): MultimediaDetail =
        MultimediaDetail(
            id = this.id,
            title = this.title,
            subtitle = this.subtitle,
            overview = this.overview,
            voteAverage = this.vote.toString(),
            image = IMAGE_BASE_URL + this.image,
            poster = IMAGE_BASE_URL + this.poster
        )
}
