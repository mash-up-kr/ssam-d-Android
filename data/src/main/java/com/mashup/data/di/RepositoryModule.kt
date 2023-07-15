package com.mashup.data.di

import com.mashup.data.repository.KeywordRepositoryImpl
import com.mashup.data.repository.OnboardingRepositoryImpl
import com.mashup.data.repository.SignalRepositoryImpl
import com.mashup.domain.repository.KeywordRepository
import com.mashup.domain.repository.LoginRepository
import com.mashup.data.repository.UserRepositoryImpl
import com.mashup.domain.repository.UserRepository
import com.mashup.domain.repository.OnboardingRepository
import com.mashup.domain.repository.SignalRepository
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

    @Binds
    @Singleton
    fun bindKeywordRepository(keywordRepositoryImpl: KeywordRepositoryImpl): KeywordRepository

    @Binds
    @Singleton
    fun bindSignalRepository(signalRepositoryImpl: SignalRepositoryImpl): SignalRepository
}