package com.mashup.ssamd

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mashup.presentation.navigation.MainActivity
import timber.log.Timber

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/29
 */
class KeyLinkService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e("SSSS","Device Token : $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)


        val title = message.notification?.title
        val body = message.notification?.body

        Timber.e("${title}")
//        message.notification?.imageUrl
//        val imageUrl = message.notification?.imageUrl

        if (title != null && body != null) {
            createNotificationChannel()
            notifyKeyLinkPush(title, body)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun notifyKeyLinkPush(title: String, body: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(
                this,
                PENDING_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        } else {
            PendingIntent.getActivity(
                this,
                PENDING_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        }

        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setSmallIcon(R.mipmap.ic_launcher)  // Icon
            setWhen(1)  // 시간
            setContentTitle(title)  // 제목
            setContentText(body)  // 텍스트
            priority = NotificationCompat.PRIORITY_HIGH
            setAutoCancel(false)
//            setContentIntent(pendingIntent)
            setFullScreenIntent(pendingIntent, true)
        }.build()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        NotificationManagerCompat.from(this).notify(
            notificationId++,
            notificationBuilder
        )
    }

    companion object {
        private const val CHANNEL_ID = "KeyLinkNotificationChannel"
        private const val CHANNEL_NAME = "KeyLink 알림"
        private const val PENDING_REQUEST_CODE = 1
        private var notificationId = 1
    }
}