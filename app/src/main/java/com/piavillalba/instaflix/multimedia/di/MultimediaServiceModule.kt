package com.piavillalba.instaflix.multimedia.di

import com.piavillalba.instaflix.multimedia.data.network.MultimediaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object MultimediaServiceModule {

    @Provides
    @Singleton
    fun provideMultimediaService(
        retrofit: Retrofit
    ): MultimediaService {
        return retrofit.create(MultimediaService::class.java)
    }
}
