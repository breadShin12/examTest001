package com.sjh.sample.di

import com.sjh.sample.data.remote.WeatherDataSource
import com.sjh.sample.data.repo.WeatherRepoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideMainRepo(remoteDataSource : WeatherDataSource): WeatherRepoImp {
        return WeatherRepoImp(remoteDataSource)
    }
}