package com.mashup.presentation.common.util.permission.handler

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.mashup.presentation.common.util.permission.PermissionType

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/10
 */
class NotificationPermissionHandler : PermissionHandler {
    override val permission: String
        get() = Manifest.permission.POST_NOTIFICATIONS
    override val permissionType: PermissionType
        get() = PermissionType.Notification

    override fun hasPermission(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            NotificationManagerCompat.from(context).areNotificationsEnabled()
        } else {
            true
        }
    }

    override fun permissionLauncher(
        fragment: Fragment,
        isPermissionGranted: (Boolean) -> Unit
    ): ActivityResultLauncher<String> {
        return super.permissionLauncher(fragment, isPermissionGranted)
    }

    override fun requestPermission(
        fragment: Fragment,
        isPermissionGranted: (Boolean) -> Unit
    ) {
        super.requestPermission(fragment, isPermissionGranted)
    }
}