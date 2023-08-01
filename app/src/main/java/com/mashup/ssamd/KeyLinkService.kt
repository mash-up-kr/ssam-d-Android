package com.mashup.ssamd

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mashup.ssamd.notification.ShowPushNotificationUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/29
 */
@AndroidEntryPoint
class KeyLinkService : FirebaseMessagingService() {

    @Inject
    lateinit var showPushNotificationUseCase: ShowPushNotificationUseCase

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("SSSS","Device Token : $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        showPushNotificationUseCase.invoke(remoteMessage)
    }
}