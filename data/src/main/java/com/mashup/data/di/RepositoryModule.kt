package com.mashup.data.di

import com.mashup.data.repository.UserRepositoryImpl
import com.mashup.data.repository.OnboardingRepositoryImpl
import com.mashup.domain.repository.UserRepository
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
    fun bindUserRepository(userRepository: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    fun bindOnboardingRepository(onboardingRepository: OnboardingRepositoryImpl): OnboardingRepository
}