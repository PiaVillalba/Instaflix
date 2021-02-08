package com.piavillalba.instaflix.multimedia.ui

import com.piavillalba.core.model.CoroutineContextProvider
import com.piavillalba.instaflix.AbstractPresenter
import com.piavillalba.instaflix.multimedia.domain.usecase.GetMoviesUseCase
import com.piavillalba.instaflix.multimedia.domain.usecase.GetTvshowsUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MultimediaPresenter(
    coroutineContextProvider: CoroutineContextProvider,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getTvshowsUseCase: GetTvshowsUseCase
) : MultimediaContract.Presenter,
    AbstractPresenter<MultimediaContract.View>(coroutineContextProvider) {

    override fun onViewCreated(multimediaType: MultimediaType) {
        view?.showSkeleton()
        when (multimediaType) {
            MultimediaType.MOVIES -> fetchMovies()
            MultimediaType.TVSHOWS -> fetchTvShows()
        }
    }

    private fun fetchMovies() {
        launch {
            getMoviesUseCase()
                .catch {
                    cause ->
                    throw cause
                }.collect {
                    withContext(coroutineContextProvider.mainContext) {
                        view?.run {
                            hideRefresh()
                            hideSkeleton()
                            loadMultimediaList(it)
                        }
                    }
                }
        }
    }

    private fun fetchTvShows() {
        launch {
            getTvshowsUseCase()
                .catch {
                    cause ->
                    throw cause
                }.collect {
                    withContext(coroutineContextProvider.mainContext) {
                        view?.run {
                            hideRefresh()
                            hideSkeleton()
                            loadMultimediaList(it)
                        }
                    }
                }
        }
    }
}
