package com.piavillalba.multimedia.domain.repository

import com.piavillalba.multimedia.domain.model.MultimediaItem
import kotlinx.coroutines.flow.Flow

interface MultimediaRepository {

    fun getMovies(): Flow<List<MultimediaItem>>
    fun getTvshows(): Flow<List<MultimediaItem>>
}
