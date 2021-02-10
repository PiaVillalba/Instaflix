package com.piavillalba.multimedia.domain.repository

import com.piavillalba.multimedia.domain.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenresRepository {

    fun getMovieGenres(): Flow<List<Genre>>
    fun getTvshowGenres(): Flow<List<Genre>>
}
