package com.mashup.presentation.feature.detail.message.navigation

import androidx.compose.material.SnackbarDuration
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.presentation.feature.home.navigation.navigateToHome
import com.mashup.presentation.feature.reply.signal.ReceivedSignalReplyRoute
import com.mashup.presentation.feature.reply.signal.navigation.navigateToSignalReplyRoute
import com.mashup.presentation.feature.report.ReportRoute
import com.mashup.presentation.feature.report.navigation.navigateToReceivedSignalReport
import com.mashup.presentation.feature.signal.received.ReceivedSignalDetailRoute
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/09
 */
fun NavController.navigateToChatDetail(
    roomId: Long,
    chatId: Long,
    navOptions: NavOptions? = null
) {
    navigate(
        route = KeyLinkNavigationRoute.ChatRoomGraph.ChatDetailRoute.route
            .replace("{roomId}", "$roomId")
            .replace("{chatId}", "$chatId"),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.receivedSignalGraph(
    navController: NavController,
    onBackClick: () -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
) {
    navigation(
        route = KeyLinkNavigationRoute.ReceivedSignalGraph.route,
        startDestination = KeyLinkNavigationRoute.ReceivedSignalGraph.ReceivedSignalDetailRoute.route
    ) {
        composable(
            route = KeyLinkNavigationRoute.ReceivedSignalGraph.ReceivedSignalDetailRoute.route,
            arguments = listOf(
                navArgument("signalId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            ReceivedSignalDetailRoute(
                signalId = entry.arguments?.getString("signalId")?.toLong() ?: -1,
                onBackClick = onBackClick,
                onReportMenuClick = navController::navigateToReceivedSignalReport,
                onReplyButtonClick = navController::navigateToSignalReplyRoute
            )
        }
        composable(
            route = KeyLinkNavigationRoute.ReceivedSignalGraph.ReceivedSignalReplyRoute.route,
            arguments = listOf(
                navArgument("signalId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            ReceivedSignalReplyRoute(
                signalId = entry.arguments?.getString("signalId")?.toLong() ?: -1,
                onClickBack = onBackClick,
                onShowSnackbar = onShowSnackbar,
                navigateToHome = {
                    navController.navigateToHome(
                        navOptions {
                            popUpTo(KeyLinkNavigationRoute.HomeGraph.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    )
                }
            )
        }
        composable(route = KeyLinkNavigationRoute.ReceivedSignalGraph.ReceivedSignalReportRoute.route) {
            ReportRoute(
                onBackClick = onBackClick,
                onReportIconClick = {
                    navController.navigateToHome(
                        navOptions {
                            popUpTo(KeyLinkNavigationRoute.HomeGraph.HomeRoute.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    )
                },
                onShowSnackbar = onShowSnackbar
            )
        }
    }
}