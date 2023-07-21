package com.mashup.presentation.navigation

import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.mashup.presentation.KeyLinkAppState
import com.mashup.presentation.feature.chat.navigation.chatRoomGraph
import com.mashup.presentation.feature.chat.navigation.navigateToChatRoom
import com.mashup.presentation.feature.detail.chat.navigation.navigateToChatRoomDetail
import com.mashup.presentation.feature.detail.message.navigation.chatGraph
import com.mashup.presentation.feature.detail.message.navigation.navigateToChatDetail
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
        chatRoomGraph(
            onBackClick = navController::navigateUp,
            onEmptyScreenButtonClick = navController::navigateToSignal,
            onChatRoomClick = { roomId ->
                navController.navigateToChatRoomDetail(roomId)
            },
            onMessageClick = { roomId, chatId ->
                navController.navigateToChatDetail(chatId = chatId, roomId = roomId)
            },
            onBottomSheetReportClick = navController::navigateToReport,
            nestedSignalGraph = {
                signalGraph(
                    navController = navController,
                    onBackClick = navController::navigateUp
                )
            },
            nestedMessageGraph = {
                chatGraph(
                    onBackClick = navController::navigateUp,
                    onShowSnackbar = onShowSnackbar,
                    onReportMenuClick = navController::navigateToReport,
                    onReportIconClick = {
                        navController.navigateToChatRoom(
                            navOptions {
                                popUpTo(
                                    route = KeyLinkNavigationRoute.ChatRoomGraph.ChatRoomDetailRoute.route,
                                    popUpToBuilder = { inclusive = true }
                                )
                            }
                        )
                    },
                    onReplyButtonClick = { roomId ->
                        navController.navigateToReplyRoute(roomId = roomId)
                    },
                    navigateToChatDetail = { roomId ->
                        navController.navigateToChatDetail(
                            roomId = roomId,
                            navOptions {
                                popUpTo(KeyLinkNavigationRoute.ChatGraph.ChatDetailRoute.route)
                            }
                        )
                    }
                )
            }
        )
    }
}