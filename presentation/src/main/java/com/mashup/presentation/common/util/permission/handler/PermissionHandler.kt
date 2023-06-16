package com.mashup.presentation.common.util.permission.handler

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.mashup.presentation.common.util.permission.PermissionType

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/10
 */
interface PermissionHandler {

    val permission: String
    val permissionType: PermissionType

    fun hasPermission(context: Context): Boolean

    fun permissionLauncher(
        activity: FragmentActivity,
        isPermissionGranted: (Boolean) -> Unit,
    ): ActivityResultLauncher<String> =
        activity.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            isPermissionGranted.invoke(isGranted)
        }

    fun requestPermission(
        activity: FragmentActivity,
        isPermissionGranted: (Boolean) -> Unit
    ) {
        permissionLauncher(activity, isPermissionGranted).launch(permission)
    }

    fun checkPermission(
        context: Context,
        activity: FragmentActivity,
        isPermissionGranted: (Boolean) -> Unit,
    ) {
        if (hasPermission(context)) {
            isPermissionGranted.invoke(true)
        } else {
            requestPermission(activity, isPermissionGranted)
        }
    }
}
