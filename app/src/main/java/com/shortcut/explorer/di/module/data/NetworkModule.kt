package com.shortcut.explorer.di.module.data

import com.shortcut.explorer.BuildConfig
import com.shortcut.explorer.business.datasource.network.main.MainApiService
import com.shortcut.explorer.business.datasource.network.NetworkWrapper
import com.shortcut.explorer.business.datasource.network.search.SearchApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkWrapper():NetworkWrapper = NetworkWrapper()


    @Provides
    @Singleton
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply { level=
        HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {

            if (BuildConfig.DEBUG)
                addInterceptor(loggingInterceptor)

        }.build()
    }

    @Provides
    @Singleton
    fun provideMainApiService(okHttpClient: OkHttpClient): MainApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.MAIN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSearchApiService(okHttpClient: OkHttpClient): SearchApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.SEARCH_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}