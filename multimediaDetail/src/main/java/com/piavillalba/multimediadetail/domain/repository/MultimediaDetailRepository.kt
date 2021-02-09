package com.piavillalba.multimediadetail.domain.repository

import com.piavillalba.multimediadetail.domain.model.MultimediaDetail
import kotlinx.coroutines.flow.Flow

interface MultimediaDetailRepository {

    fun getMovieById(id: Int): Flow<MultimediaDetail>
    fun getTvshowById(id: Int): Flow<MultimediaDetail>
}
