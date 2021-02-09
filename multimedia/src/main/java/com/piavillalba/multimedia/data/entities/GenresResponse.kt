package com.piavillalba.multimedia.data.entities

import com.google.gson.annotations.SerializedName
import com.piavillalba.multimedia.domain.model.Genre

data class GenresResponse(
    @SerializedName("genres")
    val data: List<GenreItem>
)

data class GenreItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
) {

    fun toGenre(): Genre =
        Genre(
            id = this.id,
            name = this.name
        )
}
