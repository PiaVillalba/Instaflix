package com.piavillalba.multimediadetail.di

import com.piavillalba.multimediadetail.domain.repository.MultimediaDetailRepository
import com.piavillalba.multimediadetail.domain.usecase.GetMovieDetailUseCase
import com.piavillalba.multimediadetail.domain.usecase.GetTvshowDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MultimediaDetailModule {

    @ViewModelScoped
    @Provides
    fun providerGetMovieDetailUseCase(
        multimediaDetailRepository: MultimediaDetailRepository
    ): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(multimediaDetailRepository)
    }

    @ViewModelScoped
    @Provides
    fun providerGetTvshowDetailUseCase(
        multimediaDetailRepository: MultimediaDetailRepository
    ): GetTvshowDetailUseCase {
        return GetTvshowDetailUseCase(multimediaDetailRepository)
    }
}
