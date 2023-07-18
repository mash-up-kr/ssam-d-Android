package com.mashup.data.di

import com.mashup.data.repository.*
import com.mashup.domain.repository.*
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

    @Binds
    @Singleton
    fun bindChatRepository(chatRepositoryImpl: ChatRepositoryImpl): ChatRepository
}
