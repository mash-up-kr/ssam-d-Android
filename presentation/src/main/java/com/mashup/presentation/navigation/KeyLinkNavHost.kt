package com.mashup.presentation.navigation

import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.mashup.presentation.KeyLinkAppState
import com.mashup.presentation.feature.chat.navigation.chatGraph
import com.mashup.presentation.feature.chat.navigation.navigateToChat
import com.mashup.presentation.feature.detail.chat.navigation.navigateToChatDetail
import com.mashup.presentation.feature.detail.message.navigation.navigateToMessageDetail
import com.mashup.presentation.feature.guide.navigation.navigateToGuideRoute
import com.mashup.presentation.feature.home.navigation.homeGraph
import com.mashup.presentation.feature.profile.navigation.navigateToNavigationRoute
import com.mashup.presentation.feature.profile.navigation.profileGraph
import com.mashup.presentation.feature.reply.navigation.navigateToReplyRoute
import com.mashup.presentation.feature.report.navigation.navigateToReport
import com.mashup.presentation.feature.signal.navigation.navigateToSignal
import com.mashup.presentation.feature.signal.navigation.signalGraph

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
@Composable
fun KeyLinkNavHost(
    appState: KeyLinkAppState,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = KeyLinkNavigationRoute.HomeGraph.route
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeGraph(
            navController = navController,
            onGuideClick = navController::navigateToGuideRoute,
            onBackClick = navController::navigateUp,
            onShowSnackbar = onShowSnackbar,
            nestedSignalGraph = {
                signalGraph(
                    navController = navController,
                    onBackClick = navController::navigateUp
                )
            },
            nestedProfileGraph = {
                profileGraph(
                    onBackClick = navController::navigateUp,
                    onNavigateClick = { route ->
                        navController.navigateToNavigationRoute(route)
                    }
                )
            }
        )
        signalGraph(
            navController = navController,
            onBackClick = navController::navigateUp,
        )
        chatGraph(
            onShowSnackbar = onShowSnackbar,
            onBackClick = navController::navigateUp,
            onEmptyScreenButtonClick = navController::navigateToSignal,
            onChatClick = { chatId ->
                navController.navigateToChatDetail(chatId)
            },
            onMessageClick = navController::navigateToMessageDetail,
            onReportMenuClick = navController::navigateToReport,
            onReportIconClick = {
                navController.navigateToChat(
                    navOptions {
                        popUpTo(
                            route = KeyLinkNavigationRoute.ChatGraph.ChatDetailRoute.route,
                            popUpToBuilder = { inclusive = true }
                        )
                    }
                )
            },
            onReplyButtonClick = navController::navigateToReplyRoute,
            onReplySendClick = {
                navController.navigateToChatDetail(
                    chatId = 1,  // TODO: chatId 넘겨줘야 함
                    navOptions {
                        popUpTo(KeyLinkNavigationRoute.ChatGraph.ChatDetailRoute.route)
                    }
                )
            }
        )
    }
}