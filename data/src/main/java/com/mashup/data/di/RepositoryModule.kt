package com.mashup.data.di

import com.mashup.data.repository.LoginRepositoryImpl
import com.mashup.data.repository.OnboardingRepositoryImpl
import com.mashup.domain.repository.LoginRepository
import com.mashup.domain.repository.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindLoginRepository(loginRepository: LoginRepositoryImpl): LoginRepository

    @Binds
    @Singleton
    fun bindOnboardingRepository(onboardingRepository: OnboardingRepositoryImpl): OnboardingRepository
}