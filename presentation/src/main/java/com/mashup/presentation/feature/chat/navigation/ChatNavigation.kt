package com.mashup.presentation.feature.chat.navigation

import androidx.compose.material.SnackbarDuration
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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
    onChatClick: () -> Unit,
    onMessageClick: () -> Unit,
    onReportIconClick: () -> Unit,
    onReplyButtonClick: () -> Unit
) {
    navigation(
        route = KeyLinkNavigationRoute.ChatGraph.route,
        startDestination = KeyLinkNavigationRoute.ChatGraph.ChatRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.ChatGraph.ChatRoute.route) {
            ChatRoute(
                onEmptyScreenButtonClick = {},
                onChatClick = onChatClick
            )
        }

        composable(route = KeyLinkNavigationRoute.ChatGraph.ChatDetailRoute.route) {
            ChatDetailRoute(
                onBackClick = onBackClick,
                onMessageClick = onMessageClick,
                onReportClick = onReportIconClick
            )
        }

        composable(route = KeyLinkNavigationRoute.ChatGraph.MessageDetailRoute.route) {
            MessageDetailRoute(
                onBackClick = onBackClick,
                onReportIconClick = onReportIconClick,
                onReplyButtonClick = onReplyButtonClick
            )
        }

        composable(route = KeyLinkNavigationRoute.ChatGraph.ReportRoute.route) {
            ReportRoute(
                onBackClick = onBackClick
            )
        }

        composable(route = KeyLinkNavigationRoute.ChatGraph.ReplyRoute.route) {
            ReplyRoute(
                onClickBack = onBackClick,
                onShowSnackbar = onShowSnackbar
            )
        }
        nestedGraphs()  // 채팅 없을 경우 SignalGraph 연결
    }
}