package com.piavillalba.multimediadetail.di

import com.piavillalba.multimediadetail.data.MultimediaDetailRepositoryImpl
import com.piavillalba.multimediadetail.data.network.MultimediaDetailService
import com.piavillalba.multimediadetail.domain.repository.MultimediaDetailRepository
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
    fun provideMultimediaDetailRepository(
        multimediaDetailService: MultimediaDetailService
    ): MultimediaDetailRepository {
        return MultimediaDetailRepositoryImpl(multimediaDetailService)
    }
}
