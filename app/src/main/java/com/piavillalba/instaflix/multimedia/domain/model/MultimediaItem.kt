package com.piavillalba.instaflix.multimedia.domain.model

data class MultimediaItem(
    val id: Int,
    val image: String,
    val title: String,
    val genres: List<Int>,
    val voteAverage: String
)
