package com.mashup.data.di

import android.content.Context
import com.mashup.data.network.AppHeaderProvider
import com.mashup.data.network.HttpHeaderProvider
import com.mashup.data.network.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(tokenInterceptor: TokenInterceptor) =
        OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor(tokenInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideHeaderProvider(@ApplicationContext context: Context): HttpHeaderProvider {
        return AppHeaderProvider(context)
    }

    companion object {
        const val BASE_URL = "http://49.50.166.183:30000/api/"
    }
}