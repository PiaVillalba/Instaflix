package com.piavillalba.instaflix.multimedia.di

import com.piavillalba.instaflix.multimedia.data.MultimediaRepositoryImpl
import com.piavillalba.instaflix.multimedia.data.network.MultimediaService
import com.piavillalba.instaflix.multimedia.domain.repository.MultimediaRepository
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
}
