package com.piavillalba.instaflix.usecases

import com.piavillalba.instaflix.multimedia.data.MultimediaRepositoryImpl
import com.piavillalba.instaflix.multimedia.domain.model.MultimediaItem
import com.piavillalba.instaflix.multimedia.domain.usecase.GetTvshowsUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetTvshowsUseCaseTest {

    private val multimediaRepository = mockk<MultimediaRepositoryImpl>()
    private lateinit var getTvshowsUseCaseTest: GetTvshowsUseCase
    private val tvshows = listOf(
        MultimediaItem(
            id = 1,
            image = "https://greys-anatomy.jpg",
            title = "Greys Anatomy",
            genres = listOf(1, 2, 3),
            voteAverage = "4.6"
        )
    )

    init {
        every {
            multimediaRepository.getTvshows()
        } answers {
            flowOf(tvshows)
        }
    }

    @Before
    fun setUp() {
        getTvshowsUseCaseTest = GetTvshowsUseCase(multimediaRepository)
    }

    @Test
    fun `when getTvshowsUseCase is executed then should return tvshows`() {
        val response = runBlocking {
            getTvshowsUseCaseTest().toList()
        }

        verify(exactly = 1) { multimediaRepository.getTvshows() }
        assertEquals(tvshows, response[0])
    }
}
