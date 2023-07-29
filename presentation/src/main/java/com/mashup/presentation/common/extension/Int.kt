package com.mashup.presentation.common.extension

import android.content.res.Resources

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/08
 */

internal fun Int.dpToPx() = (this * Resources.getSystem().displayMetrics.density).toInt()

internal fun Int.pxToDp() = (this / Resources.getSystem().displayMetrics.density).toInt()