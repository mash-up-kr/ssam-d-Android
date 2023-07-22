package com.mashup.presentation.feature.detail.message.navigation

import androidx.compose.material.SnackbarDuration
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.presentation.feature.detail.message.compose.MessageDetailRoute
import com.mashup.presentation.feature.reply.ReplyRoute
import com.mashup.presentation.feature.report.ReportRoute
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
        route = KeyLinkNavigationRoute.ChatGraph.ChatDetailRoute.route
            .replace("{roomId}", "$roomId")
            .replace("{chatId}", "$chatId"),
        navOptions = navOptions
    )
}


fun NavGraphBuilder.chatGraph(
    fromChatRoom: Boolean,
    onBackClick: () -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    onReportMenuClick: () -> Unit,
    onReportIconClick: () -> Unit,
    onChatReplyButtonClick: (Long) -> Unit = {},
    onSignalReplyButtonClick: (Long) -> Unit = {},
    navigateToChat: (Long) -> Unit = {},
    navigateToHome: (Long) -> Unit = {},
) {
    navigation(
        route = KeyLinkNavigationRoute.ChatGraph.route,
        startDestination = if (fromChatRoom) {
            KeyLinkNavigationRoute.ChatGraph.ChatDetailRoute.route
        } else {
            KeyLinkNavigationRoute.ChatGraph.ReceivedSignalDetailRoute.route
        }
    ) {
        composable(route = KeyLinkNavigationRoute.ChatGraph.ChatDetailRoute.route,
            arguments = listOf(
                navArgument("roomId") {
                    type = NavType.StringType
                },
                navArgument("chatId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            MessageDetailRoute(
                roomId = entry.arguments?.getString("roomId")?.toLong() ?: -1,
                chatId = entry.arguments?.getString("chatId")?.toLong() ?: -1,
                onBackClick = onBackClick,
                onReportMenuClick = onReportMenuClick,
                onReplyButtonClick = onChatReplyButtonClick
            )
        }

        composable(
            route = KeyLinkNavigationRoute.ChatGraph.ReceivedSignalDetailRoute.route,
            arguments = listOf(
                navArgument("signalId") {
                    type = NavType.StringType
                },
            )
        ) { entry ->
            ReceivedSignalDetailRoute(
                signalId = entry.arguments?.getString("signalId")?.toLong() ?: -1,
                onBackClick = onBackClick,
                onReportMenuClick = onReportMenuClick,
                onReplyButtonClick = onSignalReplyButtonClick
            )
        }

        composable(route = KeyLinkNavigationRoute.ChatGraph.ReportRoute.route) {
            ReportRoute(
                onBackClick = onBackClick,
                onReportIconClick = onReportIconClick,
                onShowSnackbar = onShowSnackbar
            )
        }

        composable(
            route = KeyLinkNavigationRoute.ChatGraph.ReplyRoute.route,
            arguments = listOf(
                navArgument("roomId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            ReplyRoute(
                roomId = entry.arguments?.getString("roomId")?.toLong() ?: -1,
                onClickBack = onBackClick,
                navigateToChat = navigateToChat,
                onShowSnackbar = onShowSnackbar
            )
        }
    }
}