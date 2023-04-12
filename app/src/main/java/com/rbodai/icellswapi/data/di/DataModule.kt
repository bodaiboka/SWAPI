package com.rbodai.icellswapi.data.di

import com.rbodai.icellswapi.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    val BASE_URL_SWAPI = "https://swapi.dev/api/"

    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl(BASE_URL_SWAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }

}