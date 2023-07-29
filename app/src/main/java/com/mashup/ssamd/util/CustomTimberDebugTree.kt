package com.mashup.ssamd.util

import timber.log.Timber
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/05/13
 */
class CustomTimberDebugTree @Inject constructor() : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {
        return "${element.className}:${element.lineNumber}#${element.methodName}"
    }
}