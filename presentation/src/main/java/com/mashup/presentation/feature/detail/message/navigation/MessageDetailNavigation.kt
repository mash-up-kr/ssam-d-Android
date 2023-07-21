package com.mashup.presentation.feature.detail.message.navigation

import androidx.compose.material.SnackbarDuration
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.presentation.feature.detail.message.compose.MessageDetailRoute
import com.mashup.presentation.feature.reply.ReplyRoute
import com.mashup.presentation.feature.report.ReportRoute
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/09
 */
fun NavController.navigateToMessageDetail(
    roomId: Long,
    chatId: Long,
    navOptions: NavOptions? = null
) {
    navigate(
        route = KeyLinkNavigationRoute.MessageGraph.MessageDetailRoute.route,
        route = KeyLinkNavigationRoute.ChatGraph.MessageDetailRoute.route
            .replace("{roomId}", "$roomId")
            .replace("{chatId}", "$chatId"),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.messageGraph(
    onBackClick: () -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    onReportMenuClick: () -> Unit,
    onReportIconClick: () -> Unit,
    onReplyButtonClick: () -> Unit,
    onReplySendClick: () -> Unit,
) {
    navigation(
        route = KeyLinkNavigationRoute.MessageGraph.route,
        startDestination = KeyLinkNavigationRoute.MessageGraph.MessageDetailRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.MessageGraph.MessageDetailRoute.route) {
            MessageDetailRoute(
                onBackClick = onBackClick,
                onReportMenuClick = onReportMenuClick,
                onReplyButtonClick = onReplyButtonClick
            )
        }

        composable(route = KeyLinkNavigationRoute.MessageGraph.ReportRoute.route) {
            ReportRoute(
                onBackClick = onBackClick,
                onReportIconClick = onReportIconClick,
                onShowSnackbar = onShowSnackbar
            )
        }

        composable(route = KeyLinkNavigationRoute.MessageGraph.ReplyRoute.route) {
            ReplyRoute(
                onClickBack = onBackClick,
                onSendClick = onReplySendClick,
                onShowSnackbar = onShowSnackbar
            )
        }
    }
}