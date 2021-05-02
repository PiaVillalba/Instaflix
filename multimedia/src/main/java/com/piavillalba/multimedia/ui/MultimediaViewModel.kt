package com.piavillalba.multimedia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.piavillalba.core.base.BaseViewModel
import com.piavillalba.core.constants.DETAIL_DEEP_LINK
import com.piavillalba.core.model.CoroutineContextProvider
import com.piavillalba.core.model.MultimediaType
import com.piavillalba.multimedia.domain.model.Genre
import com.piavillalba.multimedia.domain.model.MultimediaItem
import com.piavillalba.multimedia.domain.usecase.GetGenresUseCase
import com.piavillalba.multimedia.domain.usecase.GetMoviesUseCase
import com.piavillalba.multimedia.domain.usecase.GetTvshowsUseCase
import com.piavillalba.multimedia.ui.adapter.MultimediaAdapterListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MultimediaViewModel @Inject constructor(
    coroutineContextProvider: CoroutineContextProvider,
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getTvshowsUseCase: GetTvshowsUseCase,
    private val getGenresUseCase: GetGenresUseCase
) : BaseViewModel(coroutineContextProvider), MultimediaAdapterListener {

    private val _loadMultimediaList = MutableLiveData<List<MultimediaItem>>()
    val loadMultimediaList: LiveData<List<MultimediaItem>> = _loadMultimediaList

    private val _showGenresDialog = MutableLiveData<List<Genre>>()
    val showGenresDialog: LiveData<List<Genre>> = _showGenresDialog

    private val _showSkeleton = MutableLiveData<Unit>()
    val showSkeleton: LiveData<Unit> = _showSkeleton

    private val _navigateToDetail = MutableLiveData<String>()
    val navigateToDetail: LiveData<String> = _navigateToDetail

    private lateinit var multimediaType: MultimediaType
    private lateinit var multimediaItems: List<MultimediaItem>

    fun onViewCreated(multimediaTypeArg: MultimediaType) {
        multimediaType = multimediaTypeArg
        _showSkeleton.postValue(Unit)

        when (multimediaType) {
            MultimediaType.MOVIES -> fetchMovies()
            MultimediaType.TVSHOWS -> fetchTvShows()
        }
    }

    fun actionButtomClicked() {
        launch {
            getGenresUseCase(multimediaType)
                .catch { cause ->
                    throw cause
                }.collect {
                    withContext(coroutineContextProvider.mainContext) {
                        _showGenresDialog.postValue(it)
                    }
                }
        }
    }

    fun onGenreSelected(genreId: Int) {
        val multimediaList = multimediaItems.filter {
            it.genres.contains(genreId)
        }

        _loadMultimediaList.postValue(multimediaList)
    }

    override fun onItemSelected(multimediaItem: MultimediaItem) {
        val deepLink = "$DETAIL_DEEP_LINK/${multimediaItem.type}/${multimediaItem.id}"
        _navigateToDetail.postValue(deepLink)
    }

    private fun fetchMovies() {
        launch {
            getMoviesUseCase()
                .catch { cause ->
                    throw cause
                }.collect {
                    withContext(coroutineContextProvider.mainContext) {
                        _loadMultimediaList.postValue(it)
                    }
                }
        }
    }

    private fun fetchTvShows() {
        launch {
            getTvshowsUseCase()
                .catch { cause ->
                    throw cause
                }.collect {
                    withContext(coroutineContextProvider.mainContext) {
                        _loadMultimediaList.postValue(it)
                    }
                }
        }
    }


}