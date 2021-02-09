package com.piavillalba.multimediadetail.data

import com.piavillalba.multimediadetail.data.network.MultimediaDetailService
import com.piavillalba.multimediadetail.domain.model.MultimediaDetail
import com.piavillalba.multimediadetail.domain.repository.MultimediaDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MultimediaDetailRepositoryImpl(
    private val multimediaDetailService: MultimediaDetailService
) : MultimediaDetailRepository {

    override fun getMovieById(id: Int): Flow<MultimediaDetail> =
        flow {
            val movieResponse = multimediaDetailService.getMovieById(id)
            emit(movieResponse.toMultimediaDetail())
        }

    override fun getTvshowById(id: Int): Flow<MultimediaDetail> =
        flow {
            val tvshowResponse = multimediaDetailService.getTvshowById(id)
            emit(tvshowResponse.toMultimediaDetail())
        }
}
