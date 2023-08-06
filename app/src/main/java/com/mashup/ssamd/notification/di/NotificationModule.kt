package com.mashup.ssamd.notification.di

import com.mashup.ssamd.notification.builder.NotificationBuilder
import com.mashup.ssamd.notification.builder.NotificationBuilderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/08/01
 */
@Module
@InstallIn(SingletonComponent::class)
interface NotificationModule {

    @Binds
    @Singleton
    fun bindNotificationBuilder(
        notificationBuilderImpl: NotificationBuilderImpl
    ): NotificationBuilder
}