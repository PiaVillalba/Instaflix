package com.piavillalba.multimediadetail.di

import com.piavillalba.core.model.CoroutineContextProvider
import com.piavillalba.multimediadetail.domain.repository.MultimediaDetailRepository
import com.piavillalba.multimediadetail.domain.usecase.GetMovieDetailUseCase
import com.piavillalba.multimediadetail.domain.usecase.GetTvshowDetailUseCase
import com.piavillalba.multimediadetail.ui.MultimediaDetailContract
import com.piavillalba.multimediadetail.ui.MultimediaDetailPresenter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object MultimediaDetailModule {

    @FragmentScoped
    @Provides
    fun providerGetMovieDetailUseCase(
        multimediaDetailRepository: MultimediaDetailRepository
    ): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(multimediaDetailRepository)
    }

    @FragmentScoped
    @Provides
    fun providerGetTvshowDetailUseCase(
        multimediaDetailRepository: MultimediaDetailRepository
    ): GetTvshowDetailUseCase {
        return GetTvshowDetailUseCase(multimediaDetailRepository)
    }

    @FragmentScoped
    @Provides
    fun provideMultimediaDetailPresenter(
        getMovieDetailUseCase: GetMovieDetailUseCase,
        getTvshowDetailUseCase: GetTvshowDetailUseCase,
        coroutineContextProvider: CoroutineContextProvider,
    ): MultimediaDetailContract.Presenter {
        return MultimediaDetailPresenter(
            getMovieDetailUseCase,
            getTvshowDetailUseCase,
            coroutineContextProvider
        )
    }
}
