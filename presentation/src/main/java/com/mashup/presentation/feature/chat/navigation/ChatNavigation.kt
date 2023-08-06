package com.mashup.presentation.feature.chat.navigation

import androidx.compose.material.SnackbarDuration
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.presentation.BottomSheetType
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
    onShowBottomSheet: (BottomSheetType) -> Unit
) {
    navigation(
        route = KeyLinkNavigationRoute.ChatRoomGraph.route,
        startDestination = KeyLinkNavigationRoute.ChatRoomGraph.ChatRoomRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.ChatRoomGraph.ChatRoomRoute.route) {
            ChatRoute(
                onBackClick = onBackClick,
                onEmptyScreenButtonClick = navController::navigateToSignal,
                onChatRoomClick = navController::navigateToChatRoomDetail,
                onShowBottomSheet = onShowBottomSheet,
                onShowSnackbar = onShowSnackbar
            )
        }
        composable(
            route = KeyLinkNavigationRoute.ChatRoomGraph.ChatRoomDetailRoute.route,
            arguments = listOf(
                navArgument("roomId") {
                    type = NavType.LongType
                }
            )
        ) { entry ->
            ChatDetailRoute(
                onBackClick = onBackClick,
                onMessageClick = navController::navigateToChatDetail,
                onReportClick = navController::navigateToChatReport,
                onShowSnackbar = onShowSnackbar
            )
        }
        composable(route = KeyLinkNavigationRoute.ChatRoomGraph.ChatDetailRoute.route,
            arguments = listOf(
                navArgument("roomId") {
                    type = NavType.LongType
                },
                navArgument("chatId") {
                    type = NavType.LongType
                }
            )
        ) { entry ->
            MessageDetailRoute(
                chatId = entry.arguments?.getLong("chatId") ?: -1,
                onBackClick = onBackClick,
                onReportMenuClick = navController::navigateToChatReport,
                onReplyButtonClick = {
                    val roomId = entry.arguments?.getLong("roomId") ?: -1
                    navController.navigateToChatReply(roomId)
                },
                onShowSnackbar = onShowSnackbar
            )
        }
        composable(
            route = KeyLinkNavigationRoute.ChatRoomGraph.ChatReplyRoute.route,
            arguments = listOf(
                navArgument("roomId") {
                    type = NavType.LongType
                }
            )
        ) { entry ->
            ChatReplyRoute(
                onClickBack = onBackClick,
                navigateToChat = {
                    val roomId = entry.arguments?.getLong("roomId") ?: -1

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