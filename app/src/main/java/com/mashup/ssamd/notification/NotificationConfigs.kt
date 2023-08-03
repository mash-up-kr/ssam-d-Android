package com.mashup.ssamd.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/08/01
 */
object NotificationConfigs {

    private const val SIGNAL_NOTIFICATION_ID = 1
    private const val SIGNAL_CHANNEL_ID = "KeyLinkSignal"
    private const val SIGNAL_CHANNEL_NAME = "구독 키워드 시그널 도착 알림"
    private const val CHAT_NOTIFICATION_ID = 2
    private const val CHAT_CHANNEL_ID = "KeyLinkChat"
    private const val CHAT_CHANNEL_NAME = "채팅 도착 알림"

    fun notifyReceivedSignal(
        context: Context,
        setNotificationAttrs: NotificationCompat.Builder.() -> NotificationCompat.Builder
    ) {
        initializeNotificationChannel(context)
        context.getNotificationManager().run {
            notify(
                (System.currentTimeMillis()/1000).toInt(),
                NotificationCompat.Builder(
                    context,
                    SIGNAL_CHANNEL_ID
                ).setNotificationAttrs().build()
            )
        }
    }

    fun notifyNewChat(
        context: Context,
        setNotificationAttrs: NotificationCompat.Builder.() -> NotificationCompat.Builder
    ) {
        initializeNotificationChannel(context)
        context.getNotificationManager().run {
            notify(
                (System.currentTimeMillis()/1000).toInt(),
                NotificationCompat.Builder(
                    context,
                    CHAT_CHANNEL_ID
                ).setNotificationAttrs().build()
            )
        }
    }

    private fun initializeNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.getNotificationManager().run {
                val signalChannel = NotificationChannel(
                    SIGNAL_CHANNEL_ID,
                    SIGNAL_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )

                val chatChannel = NotificationChannel(
                    CHAT_CHANNEL_ID,
                    CHAT_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_HIGH
                )
                createNotificationChannels(listOf(signalChannel, chatChannel))
            }
        }
    }

    private fun Context.getNotificationManager(): NotificationManager {
        return getSystemService(FirebaseMessagingService.NOTIFICATION_SERVICE) as NotificationManager
    }

}