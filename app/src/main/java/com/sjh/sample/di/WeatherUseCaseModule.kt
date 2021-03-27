package com.sjh.sample.di


import com.sjh.sample.data.repo.WeatherRepoImp
import com.sjh.sample.domain.usecase.WeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherUseCaseModule {
    @Provides
    @Singleton
    fun provideWeatherRepo(weatherRepoImp: WeatherRepoImp): WeatherUseCase {
        return WeatherUseCase(weatherRepoImp)
    }
}