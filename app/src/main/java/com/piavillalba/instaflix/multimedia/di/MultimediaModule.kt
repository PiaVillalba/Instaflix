package com.piavillalba.instaflix.multimedia.di

import com.piavillalba.core.model.CoroutineContextProvider
import com.piavillalba.instaflix.multimedia.domain.repository.MultimediaRepository
import com.piavillalba.instaflix.multimedia.domain.usecase.GetMoviesUseCase
import com.piavillalba.instaflix.multimedia.domain.usecase.GetTvshowsUseCase
import com.piavillalba.instaflix.multimedia.ui.MultimediaContract
import com.piavillalba.instaflix.multimedia.ui.MultimediaPresenter
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
