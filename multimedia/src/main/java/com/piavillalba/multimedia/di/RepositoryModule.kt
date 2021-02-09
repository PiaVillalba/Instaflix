package com.piavillalba.multimedia.di

import com.piavillalba.multimedia.data.GenresRepositoryImpl
import com.piavillalba.multimedia.data.MultimediaRepositoryImpl
import com.piavillalba.multimedia.data.network.MultimediaService
import com.piavillalba.multimedia.domain.repository.GenresRepository
import com.piavillalba.multimedia.domain.repository.MultimediaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMoviesRepository(
        multimediaService: MultimediaService
    ): MultimediaRepository {
        return MultimediaRepositoryImpl(multimediaService)
    }

    @Provides
    @Singleton
    fun provideGenresRepository(
        multimediaService: MultimediaService
    ): GenresRepository {
        return GenresRepositoryImpl(multimediaService)
    }
}
