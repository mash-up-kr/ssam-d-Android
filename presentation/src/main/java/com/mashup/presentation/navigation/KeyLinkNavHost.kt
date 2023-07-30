package com.mashup.presentation.navigation

import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mashup.presentation.BottomSheetType
import com.mashup.presentation.KeyLinkAppState
import com.mashup.presentation.feature.chat.navigation.chatRoomGraph
import com.mashup.presentation.feature.detail.message.navigation.receivedSignalGraph
import com.mashup.presentation.feature.home.navigation.homeGraph
import com.mashup.presentation.feature.profile.navigation.profileGraph
import com.mashup.presentation.feature.signal.send.navigation.signalGraph

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
@Composable
fun KeyLinkNavHost(
    appState: KeyLinkAppState,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    controlBottomSheet: (BottomSheetType) -> Unit,
    onBackClick: () -> Unit,
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
            onBackClick = navController::navigateUp,
            onShowSnackbar = onShowSnackbar,
        )
        signalGraph(
            navController = navController,
            onBackClick = navController::navigateUp,
        )
        chatRoomGraph(
            navController = navController,
            onBackClick = onBackClick,
            onShowSnackbar = onShowSnackbar,
            controlBottomSheet = controlBottomSheet
        )
        profileGraph(
            navController = navController,
            onBackClick = navController::navigateUp,
        )
        receivedSignalGraph(
            navController = navController,
            onBackClick = navController::navigateUp,
            onShowSnackbar = onShowSnackbar
        )
    }
}