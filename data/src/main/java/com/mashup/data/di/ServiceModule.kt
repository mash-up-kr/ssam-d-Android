package com.mashup.data.di

import com.mashup.data.source.remote.service.LoginService
import com.mashup.data.source.remote.service.OnboardingService
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
    fun provideLoginService(retrofit: Retrofit): LoginService =
        retrofit.create(LoginService::class.java)

    @Provides
    @Singleton
    fun provideOnboardingService(retrofit: Retrofit): OnboardingService =
        retrofit.create(OnboardingService::class.java)
}