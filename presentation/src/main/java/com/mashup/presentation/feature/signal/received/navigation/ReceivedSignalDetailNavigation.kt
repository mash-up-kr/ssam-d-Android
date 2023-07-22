package com.mashup.presentation.feature.signal.received.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/22
 */

fun NavController.navigateToReceivedSignalDetail(
    signalId: Long,
    navOptions: NavOptions? = null
) {
    navigate(
        route = KeyLinkNavigationRoute.ReceivedSignalGraph.ReceivedSignalDetailRoute.route.replace(
            "{signalId}",
            "$signalId"
        ),
        navOptions = navOptions
    )
}