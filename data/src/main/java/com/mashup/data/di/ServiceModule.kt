package com.mashup.data.di

import com.mashup.data.source.remote.service.KeywordService
import com.mashup.data.source.remote.service.UserService
import com.mashup.data.source.remote.service.OnboardingService
import com.mashup.data.source.remote.service.SignalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideOnboardingService(retrofit: Retrofit): OnboardingService =
        retrofit.create(OnboardingService::class.java)

    @Provides
    @Singleton
    fun provideKeywordService(retrofit: Retrofit): KeywordService =
        retrofit.create(KeywordService::class.java)

    @Provides
    @Singleton
    fun provideSignalService(retrofit: Retrofit): SignalService =
        retrofit.create(SignalService::class.java)
}