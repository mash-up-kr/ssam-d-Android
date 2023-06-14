package com.mashup.presentation.common.util.permission.manager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.mashup.presentation.common.util.permission.PermissionType
import com.mashup.presentation.common.util.permission.handler.NotificationPermissionHandler
import com.mashup.presentation.common.util.permission.handler.PermissionHandler

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/10
 */
class PermissionManagerImpl : PermissionManager {

    private fun getPermissionHandler(
        permission: PermissionType
    ): PermissionHandler {
        return when (permission) {
            PermissionType.Notification -> NotificationPermissionHandler()
        }
    }

    override fun hasPermission(context: Context, permission: PermissionType): Boolean =
        getPermissionHandler(permission).hasPermission(context)


    override fun requestPermission(
        fragment: Fragment,
        permission: PermissionType,
        isPermissionGranted: (Boolean) -> Unit
    ) = getPermissionHandler(permission).requestPermission(fragment, isPermissionGranted)


    override fun performPermission(
        fragment: Fragment,
        permission: PermissionType,
        isPermissionGranted: (Boolean) -> Unit
    ) = getPermissionHandler(permission).checkPermission(fragment.requireContext(), fragment, isPermissionGranted)

}