package com.jemy.hiltapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jemy.hiltapp.retrofit.BlogRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGson():Gson =
        GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

    @Singleton
    @Provides
    fun provideRetrofit(gson:Gson) :Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson))

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): BlogRetrofit {
        return retrofit
            .build()
            .create(BlogRetrofit::class.java)
    }
}