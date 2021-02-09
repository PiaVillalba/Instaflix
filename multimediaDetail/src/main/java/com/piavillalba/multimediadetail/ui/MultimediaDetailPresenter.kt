package com.piavillalba.multimediadetail.ui

import com.piavillalba.core.base.AbstractPresenter
import com.piavillalba.core.model.CoroutineContextProvider
import com.piavillalba.core.model.MultimediaType
import com.piavillalba.multimediadetail.domain.usecase.GetMovieDetailUseCase
import com.piavillalba.multimediadetail.domain.usecase.GetTvshowDetailUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MultimediaDetailPresenter(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getTvshowDetailUseCase: GetTvshowDetailUseCase,
    coroutineContextProvider: CoroutineContextProvider,
) : MultimediaDetailContract.Presenter,
    AbstractPresenter<MultimediaDetailContract.View>(coroutineContextProvider) {

    override fun onViewCreated(type: MultimediaType, idMultimedia: String) {
        when (type) {
            MultimediaType.MOVIES -> fetchMovieById(idMultimedia)
            MultimediaType.TVSHOWS -> fetchTvshowById(idMultimedia)
        }
    }

    private fun fetchMovieById(id: String) {
        launch {
            getMovieDetailUseCase(id.toInt())
                .catch {
                    cause ->
                    throw cause
                }.collect {
                    withContext(coroutineContextProvider.mainContext) {
                        view?.run {
                            loadMultimediaDetail(it)
                        }
                    }
                }
        }
    }

    private fun fetchTvshowById(id: String) {
        launch {
            getTvshowDetailUseCase(id.toInt())
                .catch { cause ->
                    throw cause
                }.collect {
                    withContext(coroutineContextProvider.mainContext) {
                        view?.run {
                            loadMultimediaDetail(it)
                        }
                    }
                }
        }
    }
}
