package com.piavillalba.multimediadetail.usecases

import com.piavillalba.multimediadetail.data.MultimediaDetailRepositoryImpl
import com.piavillalba.multimediadetail.domain.model.MultimediaDetail
import com.piavillalba.multimediadetail.domain.usecase.GetTvshowDetailUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetTvshowDetailUseCaseTest {

    private val multimediaDetailRepository = mockk<MultimediaDetailRepositoryImpl>()
    private lateinit var getTvshowDetailUseCase: GetTvshowDetailUseCase

    private val tvshow = MultimediaDetail(
        id = 1,
        title = "Harry Potter",
        subtitle = "La piedra filosofal",
        overview = "Harry Potter and the Philosopher's Stone is a fantasy novel written by " +
            "British author J. K. Rowling. The first novel in the Harry Potter series and " +
            "Rowling's debut novel, it follows Harry Potter, a young wizard who discovers " +
            "his magical heritage on his eleventh birthday, when he receives a letter of" +
            " acceptance to Hogwarts School of Witchcraft and Wizardry.",

        image = "https://harry-Potter.jpg",
        poster = "https://harry-Potter.jpg",
        voteAverage = "4.2"
    )

    init {
        every {
            multimediaDetailRepository.getTvshowById(1)
        } answers {
            flowOf(tvshow)
        }
    }

    @Before
    fun setUp() {
        getTvshowDetailUseCase = GetTvshowDetailUseCase(multimediaDetailRepository)
    }

    @Test
    fun `when getTvshowDetailUseCase is executed then should return tvshow`() {
        val response = runBlocking {
            getTvshowDetailUseCase(1).first()
        }

        verify(exactly = 1) {
            multimediaDetailRepository.getTvshowById(1)
        }
        assertEquals(tvshow, response)
    }
}
