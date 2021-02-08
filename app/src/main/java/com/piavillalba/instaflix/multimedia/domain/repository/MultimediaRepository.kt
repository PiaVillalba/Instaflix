package com.piavillalba.instaflix.multimedia.domain.repository

import com.piavillalba.instaflix.multimedia.domain.model.MultimediaItem
import kotlinx.coroutines.flow.Flow

interface MultimediaRepository {

    fun getMovies(): Flow<List<MultimediaItem>>
    fun getTvshows(): Flow<List<MultimediaItem>>
}
