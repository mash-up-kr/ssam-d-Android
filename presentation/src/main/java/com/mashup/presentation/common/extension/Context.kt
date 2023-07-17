package com.mashup.presentation.common.extension

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.mashup.presentation.R

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/08
 */

internal fun Context.resizeDialogFragmentWithScale(
    dialogFragment: DialogFragment,
    widthScale: Float,
    heightScale: Float
) {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val rect = windowManager.currentWindowMetrics.bounds
        windowManager.currentWindowMetrics.windowInsets
        val window = dialogFragment.dialog?.window

        val width = (rect.width() * widthScale).toInt()
        val height = (rect.height() * heightScale).toInt()
        window?.setLayout(width, height)
    } else {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val window = dialogFragment.dialog?.window

        val width = (size.x * widthScale).toInt()
        val height = (size.y * heightScale).toInt()

        window?.setLayout(width, height)
    }
}

internal fun Context.resizeDialogFragment(dialogFragment: DialogFragment) {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val rect = windowManager.currentWindowMetrics.bounds
        windowManager.currentWindowMetrics.windowInsets

        val width = (rect.width() - (resources.getDimension(R.dimen.dialog_horizontal_margin) * 2)).toInt()

        val window = dialogFragment.dialog?.window
        window?.setLayout(width, 200.toPx())
    } else {
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val width = (size.x - (resources.getDimension(R.dimen.dialog_horizontal_margin) * 2)).toInt()

        val window = dialogFragment.dialog?.window
        window?.setLayout(width, 200.toPx())
    }
}

fun Context.getAppVersion(): String {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this.applicationContext.packageManager.getPackageInfo(
            packageName, PackageManager.PackageInfoFlags.of(0L)
        ).versionName
    } else {
        this.applicationContext.packageManager.getPackageInfo(packageName, 0).versionName
    }
}

fun Int.toPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).toInt()
}


fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}