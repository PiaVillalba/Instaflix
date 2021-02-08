package com.piavillalba.instaflix.multimedia.data

import com.piavillalba.instaflix.multimedia.data.network.MultimediaService
import com.piavillalba.instaflix.multimedia.domain.model.MultimediaItem
import com.piavillalba.instaflix.multimedia.domain.repository.MultimediaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MultimediaRepositoryImpl(private val multimediaService: MultimediaService) : MultimediaRepository {

    override fun getMovies(): Flow<List<MultimediaItem>> {
        return flow {
            emit(getMoviesFromService())
        }
    }

    override fun getTvshows(): Flow<List<MultimediaItem>> {
        return flow {
            emit(getTvshowsFromService())
        }
    }

    private suspend fun getMoviesFromService(): List<MultimediaItem> {
        return multimediaService.getPopularMovies()
            .movies
            .map { it.toMultimediaItem() }
    }

    private suspend fun getTvshowsFromService(): List<MultimediaItem> {
        return multimediaService.getPopularTvshows()
            .tvshows
            .map { it.toMultimediaItem() }
    }
}
