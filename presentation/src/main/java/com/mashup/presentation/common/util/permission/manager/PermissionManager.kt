package com.mashup.presentation.common.util.permission.manager

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.mashup.presentation.common.util.permission.PermissionType

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/10
 */
interface PermissionManager {

    fun hasPermission(context: Context, permission: PermissionType): Boolean

    fun requestPermission(
        fragment: Fragment,
        permission: PermissionType,
        isPermissionGranted: (Boolean) -> Unit
    )

    fun performPermission(
        fragment: Fragment,
        permission: PermissionType,
        isPermissionGranted: (Boolean) -> Unit
    )
}