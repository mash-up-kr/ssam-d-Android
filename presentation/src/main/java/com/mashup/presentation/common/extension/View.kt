package com.mashup.presentation.common.extension

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/16
 */

fun View.makeSnackBar(
    text: String,
    actionText: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    onAction: () -> Unit
) {
    Snackbar.make(this, text, duration).setAction(actionText) {
        onAction.invoke()
    }.show()
}