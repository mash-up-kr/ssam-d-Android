package com.mashup.presentation.common.extension

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.WindowManager
import androidx.fragment.app.DialogFragment

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/08
 */

internal fun Context.resizeDialogFragment(
    dialogFragment: DialogFragment,
    widthScale: Float,
    heightScale: Float
) {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val rect = windowManager.currentWindowMetrics.bounds
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