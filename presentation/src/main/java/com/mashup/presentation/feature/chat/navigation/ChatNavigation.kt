package com.mashup.presentation.feature.chat.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.presentation.feature.chat.compose.ChatRoute
import com.mashup.presentation.feature.detail.chat.compose.ChatDetailRoute
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
    nestedSignalGraph: NavGraphBuilder.() -> Unit = {},
    nestedMessageGraph: NavGraphBuilder.() -> Unit,
    onBackClick: () -> Unit,
    onEmptyScreenButtonClick: () -> Unit,
    onBottomSheetReportClick: () -> Unit,
    onChatRoomClick: (Long) -> Unit,
    onMessageClick: (Long, Long) -> Unit,
) {
    navigation(
        route = KeyLinkNavigationRoute.ChatRoomGraph.route,
        startDestination = KeyLinkNavigationRoute.ChatRoomGraph.ChatRoomRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.ChatRoomGraph.ChatRoomRoute.route) {
            ChatRoute(
                onEmptyScreenButtonClick = onEmptyScreenButtonClick,
                onChatRoomClick = { roomId ->
                    onChatRoomClick(roomId)
                }
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
                onMessageClick = onMessageClick,
                onReportClick = onBottomSheetReportClick
            )
        }
        nestedSignalGraph()  // 채팅 없을 경우 SignalGraph 연결
        nestedMessageGraph()  // 메시지 상세 nested graph
    }
}