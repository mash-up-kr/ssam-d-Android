package com.mashup.presentation.feature.report.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/10
 */
fun NavController.navigateToChatReport(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.ChatRoomGraph.ChatReportRoute.route,
        navOptions = navOptions
    )
}

fun NavController.navigateToReceivedSignalReport(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.ReceivedSignalGraph.ReceivedSignalReportRoute.route,
        navOptions = navOptions
    )
}