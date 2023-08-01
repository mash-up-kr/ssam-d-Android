package com.mashup.ssamd.notification

import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import com.mashup.ssamd.notification.builder.NotificationBuilder
import timber.log.Timber
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/08/01
 */
class ShowPushNotificationUseCase @Inject constructor(
    private val notificationBuilder: NotificationBuilder
) {
    operator fun invoke(remoteMessage: RemoteMessage) {
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body
        val notificationType = remoteMessage.data["notiType"]
        val receivedTimeMillis = remoteMessage.data["receivedTimeMillis"]
        val roomId = remoteMessage.data["roomId"]

        if (title != null && body != null && notificationType != null && roomId != null && receivedTimeMillis != null) {
            when (notificationType) {
                SIGNAL_TYPE -> notificationBuilder.showReceivedSignalNotification(
                    title,
                    body,
                    receivedTimeMillis
                )
                CHAT_TYPE -> notificationBuilder.showNewChatNotification(
                    title,
                    body,
                    roomId,
                    receivedTimeMillis
                )
            }
        }
    }

    companion object {
        private const val SIGNAL_TYPE = "SIGNAL"
        private const val CHAT_TYPE = "CHAT"
    }
}