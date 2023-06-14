package com.mashup.presentation.common.util.permission.handler

import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
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
        fragment: Fragment,
        isPermissionGranted: (Boolean) -> Unit,
    ): ActivityResultLauncher<String> =
        fragment.registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            isPermissionGranted.invoke(isGranted)
        }

    fun requestPermission(
        fragment: Fragment,
        isPermissionGranted: (Boolean) -> Unit
    ) {
        permissionLauncher(fragment, isPermissionGranted).launch(permission)
    }

    fun checkPermission(
        context: Context,
        fragment: Fragment,
        isPermissionGranted: (Boolean) -> Unit,
    ) {
        if (hasPermission(context)) {
            isPermissionGranted.invoke(true)
        } else {
            requestPermission(fragment, isPermissionGranted)
        }
    }
}
