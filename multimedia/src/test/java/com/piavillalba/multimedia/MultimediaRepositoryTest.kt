package com.piavillalba.multimedia

import com.piavillalba.core.model.MultimediaType
import com.piavillalba.multimedia.data.MultimediaRepositoryImpl
import com.piavillalba.multimedia.data.entities.MovieResponseItem
import com.piavillalba.multimedia.data.entities.MoviesResponse
import com.piavillalba.multimedia.data.entities.TvshowsResponse
import com.piavillalba.multimedia.data.entities.TvshowsResponseItem
import com.piavillalba.multimedia.data.network.MultimediaService
import com.piavillalba.multimedia.domain.model.MultimediaItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MultimediaRepositoryTest() {

    private lateinit var multimediaRepository: MultimediaRepositoryImpl
    private val multimediaService = mockk<MultimediaService>()
    private val movieResponseItem = MovieResponseItem(
        id = 1,
        genreIds = listOf(1, 2, 3),
        title = "Harry Potter",
        image = "/image.jpg",
        vote = 4.8
    )
    private val tvshowsResponseItem = TvshowsResponseItem(
        id = 1,
        genreIds = listOf(1, 2, 3),
        title = "Greys Anatomy",
        image = "/image.jpg",
        vote = 4.6
    )
    private val movie = MultimediaItem(
        id = 1,
        genres = listOf(1, 2, 3),
        title = "Harry Potter",
        image = "https://image.tmdb.org/t/p/w200/image.jpg",
        voteAverage = "4.8",
        type = MultimediaType.MOVIES
    )
    private val tvshow = MultimediaItem(
        id = 1,
        genres = listOf(1, 2, 3),
        title = "Greys Anatomy",
        image = "https://image.tmdb.org/t/p/w200/image.jpg",
        voteAverage = "4.6",
        type = MultimediaType.TVSHOWS
    )

    init {
        coEvery {
            multimediaService.getPopularMovies()
        } answers {
            MoviesResponse(
                listOf(movieResponseItem)
            )
        }

        coEvery {
            multimediaService.getPopularTvshows()
        } answers {
            TvshowsResponse(
                listOf(tvshowsResponseItem)
            )
        }
    }

    @Before
    fun setUp() {
        multimediaRepository = MultimediaRepositoryImpl(multimediaService)
    }

    @Test
    fun `when get movies is called then should return movies`() {
        val response = runBlocking { multimediaRepository.getMovies().toList() }

        coVerify(exactly = 1) { multimediaService.getPopularMovies() }
        Assert.assertEquals(listOf(movie), response.first())
    }

    @Test
    fun `when get tvshows is called then should return tvshows`() {
        val response = runBlocking { multimediaRepository.getTvshows().toList() }

        coVerify(exactly = 1) { multimediaService.getPopularTvshows() }
        Assert.assertEquals(listOf(tvshow), response.first())
    }
}
