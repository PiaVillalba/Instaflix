package com.piavillalba.multimedia.di

import com.piavillalba.core.model.CoroutineContextProvider
import com.piavillalba.multimedia.domain.repository.MultimediaRepository
import com.piavillalba.multimedia.domain.usecase.GetMoviesUseCase
import com.piavillalba.multimedia.domain.usecase.GetTvshowsUseCase
import com.piavillalba.multimedia.ui.MultimediaContract
import com.piavillalba.multimedia.ui.MultimediaPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object MultimediaModule {

    @FragmentScoped
    @Provides
    fun providerGetMoviesUseCase(
        getMultimediaRepository: MultimediaRepository
    ): GetMoviesUseCase {
        return GetMoviesUseCase(getMultimediaRepository)
    }

    @FragmentScoped
    @Provides
    fun providerGetTvshowsUseCase(
        getMultimediaRepository: MultimediaRepository
    ): GetTvshowsUseCase {
        return GetTvshowsUseCase(getMultimediaRepository)
    }

    @FragmentScoped
    @Provides
    fun provideMoviePresenter(
        coroutineContextProvider: CoroutineContextProvider,
        getMoviesUseCase: GetMoviesUseCase,
        getTvshowsUseCase: GetTvshowsUseCase
    ): MultimediaContract.Presenter {
        return MultimediaPresenter(
            coroutineContextProvider,
            getMoviesUseCase,
            getTvshowsUseCase
        )
    }
}
