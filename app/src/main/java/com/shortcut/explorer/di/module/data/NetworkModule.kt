package com.shortcut.explorer.di.module.data

import com.shortcut.explorer.BuildConfig
import com.shortcut.explorer.data.network.MainApiService
import com.shortcut.explorer.data.network.NetworkWrapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMainApiService(retrofit: Retrofit): MainApiService {
        return retrofit.create()
    }
}