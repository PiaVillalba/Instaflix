package com.piavillalba.instaflix.multimedia.data.entities

import com.google.gson.annotations.SerializedName
import com.piavillalba.core.constants.IMAGE_BASE_URL
import com.piavillalba.instaflix.multimedia.domain.model.MultimediaItem

data class TvshowsResponse(
    @SerializedName("results")
    val tvshows: List<TvshowsResponseItem>
)

data class TvshowsResponseItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("name")
    val title: String,
    @SerializedName("poster_path")
    val image: String,
    @SerializedName("vote_average")
    val vote: Double,
) {

    fun toMultimediaItem(): MultimediaItem =
        MultimediaItem(
            id = this.id,
            image = IMAGE_BASE_URL + this.image,
            title = this.title,
            voteAverage = this.vote.toString(),
            genres = this.genreIds

        )
}
