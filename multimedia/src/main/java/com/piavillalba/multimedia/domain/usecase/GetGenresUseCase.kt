package com.piavillalba.multimedia.domain.usecase

import com.piavillalba.core.model.MultimediaType
import com.piavillalba.multimedia.domain.model.Genre
import com.piavillalba.multimedia.domain.repository.GenresRepository
import kotlinx.coroutines.flow.Flow

class GetGenresUseCase(private val genresRepository: GenresRepository) {

    operator fun invoke(multimediaType: MultimediaType): Flow<List<Genre>> {

        return when (multimediaType) {
            MultimediaType.MOVIES -> genresRepository.getMovieGenres()
            MultimediaType.TVSHOWS -> genresRepository.getTvshowGenres()
        }
    }
}
