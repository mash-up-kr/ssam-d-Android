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
    nestedSignalGraph: NavGraphBuilder.() -> Unit = {},
    nestedMessageGraph: NavGraphBuilder.() -> Unit,
    onBackClick: () -> Unit,
    onEmptyScreenButtonClick: () -> Unit,
    onChatClick: (Long) -> Unit,
    onMessageClick: () -> Unit,
    onBottomSheetReportClick: () -> Unit,
    onChatRoomClick: (Long) -> Unit,
    onMessageClick: (Long, Long) -> Unit,
    onReportMenuClick: () -> Unit,
    onReportIconClick: () -> Unit,
    onReplyButtonClick: (Long) -> Unit,
    navigateToChatDetail: (Long) -> Unit,
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
                onReportClick = onBottomSheetReportClick
            )
        }
        nestedSignalGraph()  // 채팅 없을 경우 SignalGraph 연결
        nestedMessageGraph()  // 메시지 상세 nested graph

        composable(route = KeyLinkNavigationRoute.ChatGraph.MessageDetailRoute.route,
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

        composable(route = KeyLinkNavigationRoute.ChatGraph.ReplyRoute.route) {entry ->
            ReplyRoute(
                roomId = entry.arguments?.getString("roomId")?.toLong() ?: -1,
                onClickBack = onBackClick,
                navigateToChatDetail = navigateToChatDetail,
                onShowSnackbar = onShowSnackbar
            )
        }
        nestedGraphs()  // 채팅 없을 경우 SignalGraph 연결
    }
}