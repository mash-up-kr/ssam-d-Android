package com.mashup.ssamd.notification.builder

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.mashup.presentation.common.extension.getDisplayedTime
import com.mashup.presentation.navigation.MainActivity
import com.mashup.ssamd.R
import com.mashup.ssamd.notification.NotificationConfigs
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/08/01
 */
class NotificationBuilderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : NotificationBuilder {

    override fun showReceivedSignalNotification(
        title: String,
        body: String,
        receivedTimeMillis: String
    ) {
        NotificationConfigs.notifyReceivedSignal(context) {
            setStyle(NotificationCompat.BigTextStyle().bigText(body))
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(receivedTimeMillis.toLong())
            setContentTitle(title)
            setContentText(body)
            priority = NotificationCompat.PRIORITY_HIGH
            setAutoCancel(true)
            setFullScreenIntent(makeLauncherPendingIntent(), true)
        }
    }

    override fun showNewChatNotification(
        title: String,
        body: String,
        roomId: String,
        receivedTimeMillis: String
    ) {
        NotificationConfigs.notifyNewChat(context) {
            setStyle(NotificationCompat.BigTextStyle().bigText(body))
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(receivedTimeMillis.toLong())
            setContentTitle(title)
            setContentText(body)
            priority = NotificationCompat.PRIORITY_HIGH
            setAutoCancel(true)
            setFullScreenIntent(makeLauncherPendingIntent(), true)
        }
    }

    private fun createMainIntent(): Intent {
        return Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

    private fun makeLauncherPendingIntent(): PendingIntent {
        val intent = createMainIntent()
        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_ONE_SHOT
        } else {
            PendingIntent.FLAG_ONE_SHOT
        }

        return PendingIntent.getActivity(
            context,
            PENDING_REQUEST_CODE,
            intent,
            pendingIntentFlags
        )
    }

    companion object {
        private const val PENDING_REQUEST_CODE = 1
    }
}