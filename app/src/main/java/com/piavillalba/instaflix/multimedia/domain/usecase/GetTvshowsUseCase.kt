package com.piavillalba.instaflix.multimedia.domain.usecase

import com.piavillalba.instaflix.multimedia.domain.model.MultimediaItem
import com.piavillalba.instaflix.multimedia.domain.repository.MultimediaRepository
import kotlinx.coroutines.flow.Flow

class GetTvshowsUseCase(private val multimediaRepository: MultimediaRepository) {

    operator fun invoke(): Flow<List<MultimediaItem>> {
        return multimediaRepository.getTvshows()
    }
}
