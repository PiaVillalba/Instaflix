package com.piavillalba.multimediadetail.domain.usecase

import com.piavillalba.multimediadetail.domain.model.MultimediaDetail
import com.piavillalba.multimediadetail.domain.repository.MultimediaDetailRepository
import kotlinx.coroutines.flow.Flow

class GetTvshowDetailUseCase(
    private val multimediaDetailRepository: MultimediaDetailRepository
) {

    operator fun invoke(id: Int): Flow<MultimediaDetail> =
        multimediaDetailRepository.getTvshowById(id)
}
