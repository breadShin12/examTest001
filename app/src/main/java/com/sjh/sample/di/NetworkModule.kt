package com.sjh.sample.di

import com.sjh.sample.data.remote.WeatherApiService
import com.sjh.sample.data.remote.WeatherDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    companion object {
        const val BASE_URL = "https://www.metaweather.com"
    }

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            addNetworkInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        }
    }

    @Singleton
    @Provides
    fun provideWeatherDataSource(apiService : WeatherApiService) = WeatherDataSource(apiService)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): WeatherApiService {
        return retrofit.create(WeatherApiService::class.java)
    }
}