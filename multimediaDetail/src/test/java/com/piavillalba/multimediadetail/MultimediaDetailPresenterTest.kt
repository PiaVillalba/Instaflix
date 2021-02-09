package com.piavillalba.multimediadetail

import com.piavillalba.core.model.MultimediaType
import com.piavillalba.multimediadetail.domain.model.MultimediaDetail
import com.piavillalba.multimediadetail.domain.usecase.GetMovieDetailUseCase
import com.piavillalba.multimediadetail.domain.usecase.GetTvshowDetailUseCase
import com.piavillalba.multimediadetail.ui.MultimediaDetailContract
import com.piavillalba.multimediadetail.ui.MultimediaDetailPresenter
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

class MultimediaDetailPresenterTest {

    @get: Rule
    private val coroutineTestRule = CoroutineTestRule()

    private lateinit var presenter: MultimediaDetailContract.Presenter
    private val view = mockk<MultimediaDetailContract.View>(relaxed = true)
    private val getMovieDetailUseCase = mockk<GetMovieDetailUseCase>()
    private val getTvshowDetailUseCase = mockk<GetTvshowDetailUseCase>()
    private val multimediaDetail = MultimediaDetail(
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

    @Before
    fun setUp() {
        presenter = MultimediaDetailPresenter(
            getMovieDetailUseCase,
            getTvshowDetailUseCase,
            coroutineTestRule.coroutineContextProvider
        )

        presenter.bind(view)
    }

    @Test
    fun `when getMovieDetailUseCase is called then should show movies`() {
        coEvery {
            getMovieDetailUseCase(1)
        } answers {
            flowOf(multimediaDetail)
        }

        presenter.onViewCreated(MultimediaType.MOVIES, "1")

        coVerifyOrder {
            getMovieDetailUseCase(1)
            view.loadMultimediaDetail(multimediaDetail)
        }

        coVerify(exactly = 0) {
            getTvshowDetailUseCase(1)
        }
    }

    @Test(expected = Exception::class)
    fun `when getMovieDetailUseCase return an exception then should throw an error`() {
        coEvery {
            getMovieDetailUseCase(1)
        } answers {
            throw Exception("Error Message")
        }

        presenter.onViewCreated(MultimediaType.MOVIES, "1")

        coVerify(exactly = 1) {
            getMovieDetailUseCase(1)
        }

        verify(exactly = 1) {
            Exception("Error message")
        }

        verify(exactly = 0) {
            getTvshowDetailUseCase(1)
            view.loadMultimediaDetail(multimediaDetail)
        }
    }

    @Test
    fun `when getTvShowDetail is called then should show movies`() {
        coEvery {
            getTvshowDetailUseCase(1)
        } answers {
            flowOf(multimediaDetail)
        }

        presenter.onViewCreated(MultimediaType.TVSHOWS, "1")

        coVerifyOrder {
            getTvshowDetailUseCase(1)
            view.loadMultimediaDetail(multimediaDetail)
        }

        coVerify(exactly = 0) {
            getMovieDetailUseCase(1)
        }
    }

    @Test(expected = Exception::class)
    fun `when getTvshows return an exception then should throw an error`() {
        coEvery {
            getTvshowDetailUseCase(1)
        } answers {
            throw Exception("Error Message")
        }

        presenter.onViewCreated(MultimediaType.TVSHOWS, "1")

        coVerify(exactly = 1) {
            getTvshowDetailUseCase(1)
        }

        verify(exactly = 1) {
            Exception("Error message")
        }

        verify(exactly = 0) {
            getMovieDetailUseCase(1)
            view.loadMultimediaDetail(multimediaDetail)
        }
    }

    @After
    fun clear() {
        presenter.unBind()
    }
}
