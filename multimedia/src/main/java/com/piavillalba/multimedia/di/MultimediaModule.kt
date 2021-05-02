package com.piavillalba.multimedia.di

import com.piavillalba.multimedia.domain.repository.GenresRepository
import com.piavillalba.multimedia.domain.repository.MultimediaRepository
import com.piavillalba.multimedia.domain.usecase.GetGenresUseCase
import com.piavillalba.multimedia.domain.usecase.GetMoviesUseCase
import com.piavillalba.multimedia.domain.usecase.GetTvshowsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MultimediaModule {

    @ViewModelScoped
    @Provides
    fun providerGetMoviesUseCase(
        multimediaRepository: MultimediaRepository
    ): GetMoviesUseCase {
        return GetMoviesUseCase(multimediaRepository)
    }

    @ViewModelScoped
    @Provides
    fun providerGetTvshowsUseCase(
        multimediaRepository: MultimediaRepository
    ): GetTvshowsUseCase {
        return GetTvshowsUseCase(multimediaRepository)
    }

    @ViewModelScoped
    @Provides
    fun providerGetGenresUseCase(
        genresRepository: GenresRepository
    ): GetGenresUseCase {
        return GetGenresUseCase(genresRepository)
    }

}
