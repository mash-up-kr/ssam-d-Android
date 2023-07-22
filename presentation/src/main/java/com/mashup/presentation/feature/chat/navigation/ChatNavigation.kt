package com.mashup.presentation.feature.chat.navigation

import androidx.compose.material.SnackbarDuration
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.presentation.feature.chat.compose.ChatRoute
import com.mashup.presentation.feature.detail.chat.compose.ChatDetailRoute
import com.mashup.presentation.feature.detail.chat.navigation.navigateToChatRoomDetail
import com.mashup.presentation.feature.detail.message.compose.MessageDetailRoute
import com.mashup.presentation.feature.detail.message.navigation.navigateToChatDetail
import com.mashup.presentation.feature.reply.chat.ChatReplyRoute
import com.mashup.presentation.feature.reply.chat.navigation.navigateToChatReply
import com.mashup.presentation.feature.report.ReportRoute
import com.mashup.presentation.feature.report.navigation.navigateToChatReport
import com.mashup.presentation.feature.signal.send.navigation.navigateToSignal
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
fun NavController.navigateToChatRoom(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.ChatRoomGraph.route,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.chatRoomGraph(
    navController: NavController,
    onBackClick: () -> Unit,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
) {
    navigation(
        route = KeyLinkNavigationRoute.ChatRoomGraph.route,
        startDestination = KeyLinkNavigationRoute.ChatRoomGraph.ChatRoomRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.ChatRoomGraph.ChatRoomRoute.route) {
            ChatRoute(
                onEmptyScreenButtonClick = navController::navigateToSignal,
                onChatRoomClick = navController::navigateToChatRoomDetail
            )
        }
        composable(
            route = KeyLinkNavigationRoute.ChatRoomGraph.ChatRoomDetailRoute.route,
            arguments = listOf(
                navArgument("roomId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            ChatDetailRoute(
                roomId = entry.arguments?.getString("roomId")?.toLong() ?: -1,
                onBackClick = onBackClick,
                onMessageClick = navController::navigateToChatDetail,
                onReportClick = navController::navigateToChatReport
            )
        }
        composable(route = KeyLinkNavigationRoute.ChatRoomGraph.ChatDetailRoute.route,
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
                onReportMenuClick = navController::navigateToChatReport,
                onReplyButtonClick = navController::navigateToChatReply
            )
        }
        composable(
            route = KeyLinkNavigationRoute.ChatRoomGraph.ChatReplyRoute.route,
            arguments = listOf(
                navArgument("roomId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            ChatReplyRoute(
                roomId = entry.arguments?.getString("roomId")?.toLong() ?: -1,
                onClickBack = onBackClick,
                navigateToChat = { roomId ->
                    navController.navigateToChatRoomDetail(
                        roomId = roomId,
                        navOptions {
                            popUpTo(KeyLinkNavigationRoute.ChatRoomGraph.ChatRoomDetailRoute.route)
                            launchSingleTop = true
                        }
                    )
                },
                onShowSnackbar = onShowSnackbar
            )
        }
        composable(route = KeyLinkNavigationRoute.ChatRoomGraph.ChatReportRoute.route) {
            ReportRoute(
                onBackClick = onBackClick,
                onReportIconClick = {
                    navController.navigateToChatRoom(
                        navOptions {
                            popUpTo(KeyLinkNavigationRoute.ChatRoomGraph.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    )
                },
                onShowSnackbar = onShowSnackbar
            )
        }
    }
}