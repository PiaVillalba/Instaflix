package com.piavillalba.multimedia.ui

import com.piavillalba.core.base.AbstractPresenter
import com.piavillalba.core.constants.DETAIL_DEEP_LINK
import com.piavillalba.core.model.CoroutineContextProvider
import com.piavillalba.core.model.MultimediaType
import com.piavillalba.multimedia.domain.model.MultimediaItem
import com.piavillalba.multimedia.domain.usecase.GetMoviesUseCase
import com.piavillalba.multimedia.domain.usecase.GetTvshowsUseCase
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

    override fun onItemSelected(multimediaItem: MultimediaItem) {
        val deepLink = "$DETAIL_DEEP_LINK/${multimediaItem.type}/${multimediaItem.id}"
        view?.goToMultimediaDetail(deepLink)
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
