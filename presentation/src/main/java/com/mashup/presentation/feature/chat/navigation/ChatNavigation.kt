package com.mashup.presentation.feature.chat.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
fun NavGraphBuilder.chatGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = KeyLinkNavigationRoute.ChatGraph.route,
        startDestination = KeyLinkNavigationRoute.ChatGraph.ChatRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.ChatGraph.ChatRoute.route,) {
            // ChatRoute
        }

        composable(route = KeyLinkNavigationRoute.ChatGraph.ChatDetailRoute.route) {
            // ChatDetailRoute
        }

        composable(route = KeyLinkNavigationRoute.ChatGraph.MessageDetailRoute.route) {
            // MessageDetailRoute
        }

        composable(route = KeyLinkNavigationRoute.ChatGraph.ReportRoute.route) {
            // ReportRoute
        }
        nestedGraphs()  // 채팅 없을 경우 SignalGraph 연결
    }
}