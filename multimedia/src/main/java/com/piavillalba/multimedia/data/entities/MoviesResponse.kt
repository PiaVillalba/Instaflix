package com.piavillalba.multimedia.data.entities

import com.google.gson.annotations.SerializedName
import com.piavillalba.core.constants.IMAGE_BASE_URL
import com.piavillalba.multimedia.domain.model.MultimediaItem

data class MoviesResponse(
    @SerializedName("results")
    val movies: List<MovieResponseItem>
)

data class MovieResponseItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("original_title")
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
