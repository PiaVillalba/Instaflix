package com.piavillalba.multimediadetail.di

import com.piavillalba.multimediadetail.data.network.MultimediaDetailService
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
    ): MultimediaDetailService {
        return retrofit.create(MultimediaDetailService::class.java)
    }
}
