package com.piavillalba.instaflix

import com.piavillalba.instaflix.multimedia.domain.model.MultimediaItem
import com.piavillalba.instaflix.multimedia.domain.usecase.GetMoviesUseCase
import com.piavillalba.instaflix.multimedia.domain.usecase.GetTvshowsUseCase
import com.piavillalba.instaflix.multimedia.ui.MultimediaContract
import com.piavillalba.instaflix.multimedia.ui.MultimediaPresenter
import com.piavillalba.instaflix.multimedia.ui.MultimediaType
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

class MultimediaPresenterTest() {

    @get: Rule
    private val coroutineTestRule = CoroutineTestRule()

    private lateinit var presenter: MultimediaContract.Presenter
    private val view = mockk<MultimediaContract.View>(relaxed = true)
    private val getMoviesUseCase = mockk<GetMoviesUseCase>()
    private val getTvshowsUseCase = mockk<GetTvshowsUseCase>()
    private val multimedia = listOf(
        MultimediaItem(
            id = 1,
            image = "https://harry-Potter.jpg",
            title = "Harry Potter",
            genres = listOf(1, 2, 3),
            voteAverage = "4.2"
        )
    )

    @Before
    fun setUp() {
        presenter = MultimediaPresenter(
            coroutineTestRule.coroutineContextProvider,
            getMoviesUseCase,
            getTvshowsUseCase
        )

        presenter.bind(view)
    }

    @Test
    fun `when getMoviesUseCase is called then should show movies`() {
        coEvery {
            getMoviesUseCase()
        } answers {
            flowOf(multimedia)
        }

        presenter.onViewCreated(MultimediaType.MOVIES)

        coVerifyOrder {
            view.showSkeleton()
            getMoviesUseCase()
            with(view) {
                hideRefresh()
                hideSkeleton()
                loadMultimediaList(multimedia)
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
                loadMultimediaList(multimedia)
            }
        }
    }

    @Test
    fun `when getTvShows is called then should show movies`() {
        coEvery {
            getTvshowsUseCase()
        } answers {
            flowOf(multimedia)
        }

        presenter.onViewCreated(MultimediaType.TVSHOWS)

        coVerifyOrder {
            view.showSkeleton()
            getTvshowsUseCase()
            with(view) {
                hideRefresh()
                hideSkeleton()
                loadMultimediaList(multimedia)
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
                loadMultimediaList(multimedia)
            }
        }
    }

    @After
    fun clear() {
        presenter.unBind()
    }
}
