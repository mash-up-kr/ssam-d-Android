package com.mashup.ssamd.notification.builder

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
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

    override fun showReceivedSignalNotification(title: String, body: String) {
        NotificationConfigs.notifyReceivedSignal(context) {
            setStyle(NotificationCompat.BigTextStyle().bigText(body))
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(title)
            setContentText(body)
            priority = NotificationCompat.PRIORITY_HIGH
            setAutoCancel(true)
            setCategory(NotificationCompat.CATEGORY_MESSAGE)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setContentIntent(makeSignalLauncherPendingIntent())
        }
    }

    override fun showNewChatNotification(title: String, body: String, roomId: String) {
        NotificationConfigs.notifyNewChat(context) {
            setStyle(NotificationCompat.BigTextStyle().bigText(body))
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(title)
            setContentText(body)
            priority = NotificationCompat.PRIORITY_HIGH
            setAutoCancel(true)
            setCategory(NotificationCompat.CATEGORY_MESSAGE)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setContentIntent(makeChatLauncherPendingIntent())
        }
    }

    private fun createMainIntent(): Intent {
        return Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }

    private fun makeChatLauncherPendingIntent(): PendingIntent {
        val intent = createMainIntent()
        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_ONE_SHOT
        } else {
            PendingIntent.FLAG_ONE_SHOT
        }

        return PendingIntent.getActivity(
            context,
            (System.currentTimeMillis()).toInt(),
            intent,
            pendingIntentFlags
        )
    }

    private fun makeSignalLauncherPendingIntent(): PendingIntent {
        val intent = createMainIntent()
        val pendingIntentFlags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_ONE_SHOT
        } else {
            PendingIntent.FLAG_ONE_SHOT
        }

        return PendingIntent.getActivity(
            context,
            (System.currentTimeMillis()).toInt(),
            intent,
            pendingIntentFlags
        )
    }
}