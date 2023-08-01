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
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body
        if (title != null && body != null) {
            notificationBuilder.showReceivedSignalNotification(title, body)
        }
    }
}