package com.piavillalba.multimedia.domain.usecase

import com.piavillalba.multimedia.domain.model.MultimediaItem
import com.piavillalba.multimedia.domain.repository.MultimediaRepository
import kotlinx.coroutines.flow.Flow

class GetTvshowsUseCase(private val multimediaRepository: MultimediaRepository) {

    operator fun invoke(): Flow<List<MultimediaItem>> {
        return multimediaRepository.getTvshows()
    }
}
