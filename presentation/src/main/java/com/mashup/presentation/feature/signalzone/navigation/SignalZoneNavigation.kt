package com.mashup.presentation.feature.signalzone.navigation

import androidx.compose.material.SnackbarDuration
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.presentation.BottomSheetType
import com.mashup.presentation.feature.chat.navigation.navigateToChatRoom
import com.mashup.presentation.feature.detail.crash.CrashDetailRoute
import com.mashup.presentation.feature.detail.crash.navigation.navigateToCrashDetail
import com.mashup.presentation.feature.home.navigation.navigateToHome
import com.mashup.presentation.feature.reply.crash.CrashReplyRoute
import com.mashup.presentation.feature.reply.crash.navigation.navigateToCrashReply
import com.mashup.presentation.feature.report.ReportRoute
import com.mashup.presentation.feature.report.navigation.navigateToCrashReport
import com.mashup.presentation.feature.signalzone.compose.SignalZoneRoute
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

fun NavController.navigateToSignalZone(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.SignalZoneGraph.route,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.signalZoneGraph(
    navController: NavController,
    onShowBottomSheet: (BottomSheetType) -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    onBackClick: () -> Unit
) {
    navigation(
        route = KeyLinkNavigationRoute.SignalZoneGraph.route,
        startDestination = KeyLinkNavigationRoute.SignalZoneGraph.SignalZoneRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.SignalZoneGraph.SignalZoneRoute.route) {
            SignalZoneRoute(
                onShowBottomSheet = onShowBottomSheet,
                onCrashClick = navController::navigateToCrashDetail,
                onShowSnackbar = onShowSnackbar
            )
        }
        composable(
            route = KeyLinkNavigationRoute.SignalZoneGraph.CrashDetailRoute.route,
            arguments = listOf(
                navArgument("crashId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val crashId = entry.arguments?.getString("crashId")?.toLong() ?: -1

            CrashDetailRoute(
                crashId = crashId,
                onBackClick = onBackClick,
                onReportMenuClick = navController::navigateToCrashReport,
                onReplyButtonClick = navController::navigateToCrashReply,
                onShowSnackbar = onShowSnackbar,
            )
        }
        composable(
            route = KeyLinkNavigationRoute.SignalZoneGraph.CrashReplyRoute.route,
            arguments = listOf(
                navArgument("crashId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val crashId = entry.arguments?.getString("crashId")?.toLong() ?: -1

            CrashReplyRoute(
                crashId = crashId,
                onClickBack = onBackClick,
                navigateToHome = {
                    navController.navigateToHome(
                        navOptions {
                            popUpTo(KeyLinkNavigationRoute.HomeGraph.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    )
                },
                onShowSnackbar = onShowSnackbar
            )
        }
        composable(route = KeyLinkNavigationRoute.SignalZoneGraph.CrashReportRoute.route) {
            ReportRoute(
                onBackClick = onBackClick,
                onReportIconClick = {
                    navController.navigateToSignalZone(
                        navOptions {
                            popUpTo(KeyLinkNavigationRoute.SignalZoneGraph.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    )
                },
                onShowSnackbar = onShowSnackbar
            )
        }
    }
}
