package com.mashup.presentation.feature.chat.navigation

import androidx.compose.material.SnackbarDuration
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.presentation.feature.chat.compose.ChatRoute
import com.mashup.presentation.feature.detail.chat.compose.ChatDetailRoute
import com.mashup.presentation.feature.detail.message.compose.MessageDetailRoute
import com.mashup.presentation.feature.reply.ReplyRoute
import com.mashup.presentation.feature.report.ReportRoute
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
fun NavController.navigateToChat(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.ChatGraph.route,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.chatGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit = {},
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    onBackClick: () -> Unit,
    onEmptyScreenButtonClick: () -> Unit,
    onMessageClick: () -> Unit,
    onChatRoomClick: (Long) -> Unit,
    onReportMenuClick: () -> Unit,
    onReportIconClick: () -> Unit,
    onReplyButtonClick: () -> Unit,
    onReplySendClick: () -> Unit,
) {
    navigation(
        route = KeyLinkNavigationRoute.ChatGraph.route,
        startDestination = KeyLinkNavigationRoute.ChatGraph.ChatRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.ChatGraph.ChatRoute.route) {
            ChatRoute(
                onEmptyScreenButtonClick = onEmptyScreenButtonClick,
                onChatRoomClick = { roomId ->
                    onChatRoomClick(roomId)
                }
            )
        }

        composable(
            route = KeyLinkNavigationRoute.ChatGraph.ChatDetailRoute.route,
            arguments = listOf(
                navArgument("roomId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            ChatDetailRoute(
                roomId = entry.arguments?.getString("roomId")?.toLong() ?: -1,
                onBackClick = onBackClick,
                onMessageClick = onMessageClick,
                onReportClick = onReportIconClick
            )
        }

        composable(route = KeyLinkNavigationRoute.ChatGraph.MessageDetailRoute.route) {
            MessageDetailRoute(
                onBackClick = onBackClick,
                onReportMenuClick = onReportMenuClick,
                onReplyButtonClick = onReplyButtonClick
            )
        }

        composable(route = KeyLinkNavigationRoute.ChatGraph.ReportRoute.route) {
            ReportRoute(
                onBackClick = onBackClick,
                onReportIconClick = onReportIconClick,
                onShowSnackbar = onShowSnackbar
            )
        }

        composable(route = KeyLinkNavigationRoute.ChatGraph.ReplyRoute.route) {
            ReplyRoute(
                onClickBack = onBackClick,
                onSendClick = onReplySendClick,
                onShowSnackbar = onShowSnackbar
            )
        }
        nestedGraphs()  // 채팅 없을 경우 SignalGraph 연결
    }
}