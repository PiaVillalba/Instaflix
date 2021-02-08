package com.piavillalba.instaflix.usecases

import com.piavillalba.instaflix.multimedia.data.MultimediaRepositoryImpl
import com.piavillalba.instaflix.multimedia.domain.model.MultimediaItem
import com.piavillalba.instaflix.multimedia.domain.usecase.GetMoviesUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetMoviesUseCaseTest {

    private val multimediaRepository = mockk<MultimediaRepositoryImpl>()
    private lateinit var getMoviesUseCaseTest: GetMoviesUseCase
    private val movieList = listOf(
        MultimediaItem(
            id = 1,
            image = "https://harry-Potter.jpg",
            title = "Harry Potter",
            genres = listOf(1, 2, 3),
            voteAverage = "4.2"
        )
    )

    init {
        every {
            multimediaRepository.getMovies()
        } answers {
            flowOf(movieList)
        }
    }

    @Before
    fun setUp() {
        getMoviesUseCaseTest = GetMoviesUseCase(multimediaRepository)
    }

    @Test
    fun `when getMoviesUseCase is executed then should return movies`() {
        val response = runBlocking {
            getMoviesUseCaseTest().toList()
        }

        verify(exactly = 1) { multimediaRepository.getMovies() }
        assertEquals(movieList, response[0])
    }
}
