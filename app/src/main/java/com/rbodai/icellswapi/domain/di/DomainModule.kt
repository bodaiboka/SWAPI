package com.rbodai.icellswapi.domain.di

import com.rbodai.icellswapi.data.api.ApiService
import com.rbodai.icellswapi.data.repositoryimpl.SwapiRepoImpl
import com.rbodai.icellswapi.domain.respository.SwapiRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    fun provideSwapiRepo(apiService: ApiService): SwapiRepo {
        return SwapiRepoImpl(apiService = apiService)
    }
    
}