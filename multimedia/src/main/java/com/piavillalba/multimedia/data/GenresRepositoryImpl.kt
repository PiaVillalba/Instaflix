package com.piavillalba.multimedia.data

import com.piavillalba.multimedia.data.network.MultimediaService
import com.piavillalba.multimedia.domain.model.Genre
import com.piavillalba.multimedia.domain.repository.GenresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GenresRepositoryImpl(private val multimediaService: MultimediaService) :
    GenresRepository {

    override fun getMovieGenres(): Flow<List<Genre>> {
        return flow {
            emit(getMovieGenresFromService())
        }
    }

    override fun getTvshowGenres(): Flow<List<Genre>> {
        return flow {
            emit(getTvshowGenresFromService())
        }
    }

    private suspend fun getMovieGenresFromService(): List<Genre> {
        return multimediaService.getMovieGenres()
            .data
            .map { it.toGenre() }
    }

    private suspend fun getTvshowGenresFromService(): List<Genre> {
        return multimediaService.getTvGenres()
            .data
            .map { it.toGenre() }
    }
}
