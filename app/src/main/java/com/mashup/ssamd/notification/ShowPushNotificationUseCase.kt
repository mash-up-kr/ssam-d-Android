package com.mashup.ssamd.notification

import com.google.firebase.messaging.RemoteMessage
import com.mashup.ssamd.notification.builder.NotificationBuilder
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
        val title = remoteMessage.notification?.title.orEmpty()
        val body = remoteMessage.notification?.body.orEmpty()
//        val title = remoteMessage.data["title"]
//        val body = remoteMessage.data["body"]
        val notificationType = remoteMessage.data["notiType"].orEmpty()
        val roomId = remoteMessage.data["roomId"].orEmpty()

        if (title != null && body != null && notificationType != null && roomId != null) {
            when (notificationType) {
                SIGNAL_TYPE -> notificationBuilder.showReceivedSignalNotification(title, body)
                CHAT_TYPE -> notificationBuilder.showNewChatNotification(title, body, roomId)
            }
        }
    }

    companion object {
        private const val SIGNAL_TYPE = "SIGNAL"
        private const val CHAT_TYPE = "CHAT"
    }
}