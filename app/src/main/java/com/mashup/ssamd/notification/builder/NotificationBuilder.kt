package com.mashup.ssamd.notification.builder

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/08/01
 */
interface NotificationBuilder {

    fun showReceivedSignalNotification(title: String, body: String)

    fun showNewChatNotification()
}