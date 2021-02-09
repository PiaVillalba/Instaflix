package com.piavillalba.multimediadetail

import com.piavillalba.multimediadetail.data.MultimediaDetailRepositoryImpl
import com.piavillalba.multimediadetail.data.entities.MovieResponse
import com.piavillalba.multimediadetail.data.entities.TvshowResponse
import com.piavillalba.multimediadetail.data.network.MultimediaDetailService
import com.piavillalba.multimediadetail.domain.model.MultimediaDetail
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MultimediaDetailRepositoryTest {

    private lateinit var multimediaDetailRepository: MultimediaDetailRepositoryImpl
    private val multimediaDetailService = mockk<MultimediaDetailService>()

    init {
        val movieResponse = MovieResponse(
            id = 1,
            overview = "Harry Potter and the Philosopher's Stone is a fantasy novel written by " +
                "British author J. K. Rowling. The first novel in the Harry Potter series and " +
                "Rowling's debut novel, it follows Harry Potter, a young wizard who discovers " +
                "his magical heritage on his eleventh birthday, when he receives a letter of" +
                " acceptance to Hogwarts School of Witchcraft and Wizardry.",
            title = "Harry Potter",
            subtitle = "La piedra filosofal",
            vote = 4.2,
            image = "/harry-Potter.jpg",
            poster = "/harry-Potter.jpg"
        )
        val tvshowResponse = TvshowResponse(
            id = 2,
            overview = "Harry Potter and the Philosopher's Stone is a fantasy novel written by " +
                "British author J. K. Rowling. The first novel in the Harry Potter series and " +
                "Rowling's debut novel, it follows Harry Potter, a young wizard who discovers " +
                "his magical heritage on his eleventh birthday, when he receives a letter of" +
                " acceptance to Hogwarts School of Witchcraft and Wizardry.",
            title = "Harry Potter",
            subtitle = "La piedra filosofal",
            vote = 4.8,
            image = "/harry-Potter.jpg",
            poster = "/harry-Potter.jpg"
        )

        coEvery {
            multimediaDetailService.getMovieById(1)
        } answers {
            movieResponse
        }

        coEvery {
            multimediaDetailService.getTvshowById(2)
        } answers {
            tvshowResponse
        }
    }

    @Before
    fun setUp() {
        multimediaDetailRepository = MultimediaDetailRepositoryImpl(multimediaDetailService)
    }

    @Test
    fun `when get movie detail is called then should return movie`() {
        val movie = MultimediaDetail(
            id = 1,
            title = "Harry Potter",
            subtitle = "La piedra filosofal",
            overview = "Harry Potter and the Philosopher's Stone is a fantasy novel written by " +
                "British author J. K. Rowling. The first novel in the Harry Potter series and " +
                "Rowling's debut novel, it follows Harry Potter, a young wizard who discovers " +
                "his magical heritage on his eleventh birthday, when he receives a letter of" +
                " acceptance to Hogwarts School of Witchcraft and Wizardry.",

            image = "https://image.tmdb.org/t/p/w200/harry-Potter.jpg",
            poster = "https://image.tmdb.org/t/p/w200/harry-Potter.jpg",
            voteAverage = "4.2"
        )
        val response = runBlocking {
            multimediaDetailRepository.getMovieById(1).first()
        }

        coVerify(exactly = 1) { multimediaDetailService.getMovieById(1) }
        Assert.assertEquals(movie, response)
    }

    @Test
    fun `when get tvshow detail is called then should return tvshow`() {
        val tvshow = MultimediaDetail(
            id = 2,
            title = "Harry Potter",
            subtitle = "La piedra filosofal",
            overview = "Harry Potter and the Philosopher's Stone is a fantasy novel written by " +
                "British author J. K. Rowling. The first novel in the Harry Potter series and " +
                "Rowling's debut novel, it follows Harry Potter, a young wizard who discovers " +
                "his magical heritage on his eleventh birthday, when he receives a letter of" +
                " acceptance to Hogwarts School of Witchcraft and Wizardry.",

            image = "https://image.tmdb.org/t/p/w200/harry-Potter.jpg",
            poster = "https://image.tmdb.org/t/p/w200/harry-Potter.jpg",
            voteAverage = "4.8"
        )

        val response = runBlocking {
            multimediaDetailRepository.getTvshowById(2).first()
        }

        coVerify(exactly = 1) { multimediaDetailService.getTvshowById(2) }
        Assert.assertEquals(tvshow, response)
    }
}
