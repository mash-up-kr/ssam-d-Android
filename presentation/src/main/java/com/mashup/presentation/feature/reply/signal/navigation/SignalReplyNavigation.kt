package com.mashup.presentation.feature.reply.signal.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/22
 */
fun NavController.navigateToSignalReplyRoute(signalId: Long, navOptions: NavOptions? = null) {
    Log.e("Call?", "전환")
    navigate(
        route = KeyLinkNavigationRoute.ChatGraph.ReceivedSignalReplyRoute.route.replace(
            "{signalId}",
            "$signalId"
        ),
        navOptions = navOptions
    )
}