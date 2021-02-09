package com.piavillalba.multimedia.di

import com.piavillalba.core.model.CoroutineContextProvider
import com.piavillalba.multimedia.domain.repository.GenresRepository
import com.piavillalba.multimedia.domain.repository.MultimediaRepository
import com.piavillalba.multimedia.domain.usecase.GetGenresUseCase
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
        multimediaRepository: MultimediaRepository
    ): GetMoviesUseCase {
        return GetMoviesUseCase(multimediaRepository)
    }

    @FragmentScoped
    @Provides
    fun providerGetTvshowsUseCase(
        multimediaRepository: MultimediaRepository
    ): GetTvshowsUseCase {
        return GetTvshowsUseCase(multimediaRepository)
    }

    @FragmentScoped
    @Provides
    fun providerGetGenresUseCase(
        genresRepository: GenresRepository
    ): GetGenresUseCase {
        return GetGenresUseCase(genresRepository)
    }

    @FragmentScoped
    @Provides
    fun provideMoviePresenter(
        coroutineContextProvider: CoroutineContextProvider,
        getMoviesUseCase: GetMoviesUseCase,
        getTvshowsUseCase: GetTvshowsUseCase,
        getGenresUseCase: GetGenresUseCase
    ): MultimediaContract.Presenter {
        return MultimediaPresenter(
            coroutineContextProvider,
            getMoviesUseCase,
            getTvshowsUseCase,
            getGenresUseCase
        )
    }
}
