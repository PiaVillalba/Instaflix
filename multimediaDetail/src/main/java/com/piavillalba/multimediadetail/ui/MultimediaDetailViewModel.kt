package com.piavillalba.multimediadetail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piavillalba.core.base.BaseViewModel
import com.piavillalba.core.model.CoroutineContextProvider
import com.piavillalba.core.model.MultimediaType
import com.piavillalba.multimediadetail.domain.model.MultimediaDetail
import com.piavillalba.multimediadetail.domain.usecase.GetMovieDetailUseCase
import com.piavillalba.multimediadetail.domain.usecase.GetTvshowDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MultimediaDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getTvshowDetailUseCase: GetTvshowDetailUseCase,
    coroutineContextProvider: CoroutineContextProvider,
) : BaseViewModel(coroutineContextProvider) {

    private val _multimediaDetail = MutableLiveData<MultimediaDetail>()
    val multimediaDetail: LiveData<MultimediaDetail> = _multimediaDetail

    fun onViewCreated(type: MultimediaType, idMultimedia: String) {
        when (type) {
            MultimediaType.MOVIES -> fetchMovieById(idMultimedia)
            MultimediaType.TVSHOWS -> fetchTvshowById(idMultimedia)
        }
    }

    private fun fetchMovieById(id: String) {
        launch {
            getMovieDetailUseCase(id.toInt())
                .catch { cause ->
                    throw cause
                }.collect {
                    withContext(coroutineContextProvider.mainContext) {
                        _multimediaDetail.postValue(it)
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
                        _multimediaDetail.postValue(it)
                    }
                }
        }
    }
}
