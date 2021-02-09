package com.piavillalba.multimedia.domain.model

import com.piavillalba.core.model.MultimediaType

data class MultimediaItem(
    val id: Int,
    val image: String,
    val title: String,
    val genres: List<Int>,
    val voteAverage: String,
    val type: MultimediaType
)
