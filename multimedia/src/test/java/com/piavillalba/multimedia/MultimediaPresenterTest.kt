package com.piavillalba.multimedia

import com.piavillalba.core.model.MultimediaType
import com.piavillalba.multimedia.domain.model.MultimediaItem
import com.piavillalba.multimedia.domain.usecase.GetGenresUseCase
import com.piavillalba.multimedia.domain.usecase.GetMoviesUseCase
import com.piavillalba.multimedia.domain.usecase.GetTvshowsUseCase
import com.piavillalba.multimedia.ui.MultimediaViewModel
import com.piavillalba.test.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MultimediaPresenterTest {

    @get: Rule
    private val coroutineTestRule = CoroutineTestRule()

    private lateinit var presenter : MultimediaViewModel
    private val getMoviesUseCase = mockk<GetMoviesUseCase>()
    private val getTvshowsUseCase = mockk<GetTvshowsUseCase>()
    private val getGenresUseCase = mockk<GetGenresUseCase>()
    private val movies = listOf(
        MultimediaItem(
            id = 1,
            image = "https://harry-Potter.jpg",
            title = "Harry Potter",
            genres = listOf(1, 2, 3),
            voteAverage = "4.2",
            MultimediaType.MOVIES
        )
    )
    private val tvshows = listOf(
        MultimediaItem(
            id = 1,
            image = "https://harry-Potter.jpg",
            title = "Harry Potter",
            genres = listOf(1, 2, 3),
            voteAverage = "4.2",
            MultimediaType.TVSHOWS
        )
    )

    @Before
    fun setUp() {
        presenter = MultimediaViewModel(
            coroutineTestRule.coroutineContextProvider,
            getMoviesUseCase,
            getTvshowsUseCase,
            getGenresUseCase
        )

    }

    @Test
    fun `when getMoviesUseCase is called then should show movies`() {
        coEvery {
            getMoviesUseCase()
        } answers {
            flowOf(movies)
        }

        presenter.onViewCreated(MultimediaType.MOVIES)

        coVerifyOrder {
            view.showSkeleton()
            getMoviesUseCase()
            with(view) {
                hideRefresh()
                hideSkeleton()
                loadMultimediaList(movies)
            }
        }

        coVerify(exactly = 0) {
            getTvshowsUseCase()
        }
    }

    @Test(expected = Exception::class)
    fun `when getMoviesUseCase return an exception then should throw an error`() {
        coEvery {
            getMoviesUseCase()
        } answers {
            throw Exception("Error Message")
        }

        presenter.onViewCreated(MultimediaType.MOVIES)

        coVerifyOrder {
            view.showSkeleton()
            getMoviesUseCase()
        }

        verify(exactly = 1) {
            Exception("Error message")
        }

        verify(exactly = 0) {
            getTvshowsUseCase()
            view.run {
                hideSkeleton()
                hideRefresh()
                loadMultimediaList(movies)
            }
        }
    }

    @Test
    fun `when getTvShows is called then should show movies`() {
        coEvery {
            getTvshowsUseCase()
        } answers {
            flowOf(tvshows)
        }

        presenter.onViewCreated(MultimediaType.TVSHOWS)

        coVerifyOrder {
            view.showSkeleton()
            getTvshowsUseCase()
            with(view) {
                hideRefresh()
                hideSkeleton()
                loadMultimediaList(tvshows)
            }
        }

        coVerify(exactly = 0) {
            getMoviesUseCase()
        }
    }

    @Test(expected = Exception::class)
    fun `when getTvshows return an exception then should throw an error`() {
        coEvery {
            getTvshowsUseCase()
        } answers {
            throw Exception("Error Message")
        }

        presenter.onViewCreated(MultimediaType.TVSHOWS)

        coVerifyOrder {
            view.showSkeleton()
            getTvshowsUseCase()
        }

        verify(exactly = 1) {
            Exception("Error message")
        }

        verify(exactly = 0) {
            getMoviesUseCase()
            view.run {
                hideSkeleton()
                hideRefresh()
                loadMultimediaList(tvshows)
            }
        }
    }

}
