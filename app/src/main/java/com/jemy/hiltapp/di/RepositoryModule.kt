package com.jemy.hiltapp.di

import com.jemy.hiltapp.repository.MainRepository
import com.jemy.hiltapp.retrofit.BlogRetrofit
import com.jemy.hiltapp.retrofit.NetworkMapper
import com.jemy.hiltapp.room.BlogDao
import com.jemy.hiltapp.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository = MainRepository(blogDao, retrofit, cacheMapper, networkMapper)
}