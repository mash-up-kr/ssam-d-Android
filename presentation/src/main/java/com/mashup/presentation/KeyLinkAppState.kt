package com.mashup.presentation

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.mashup.presentation.feature.chat.navigation.navigateToChatRoom
import com.mashup.presentation.feature.home.navigation.navigateToHome
import com.mashup.presentation.feature.signal.send.navigation.navigateToSignal
import com.mashup.presentation.navigation.KeyLinkNavigationRoute
import com.mashup.presentation.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun rememberKeyLinkAppState(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(
        snackbarHostState = remember { SnackbarHostState() }
    ),
    modalBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        animationSpec = SwipeableDefaults.AnimationSpec,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false
    ),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): KeyLinkAppState {
    return remember(navController, scaffoldState, modalBottomSheetState, coroutineScope) {
        KeyLinkAppState(
            navController,
            scaffoldState,
            modalBottomSheetState,
            coroutineScope
        )
    }
}

/**
 * KeyLinkAppState에는 전반적인 어플리케이션 자체의 상태가 포함됩니다.
 * 1. Navigation Destination
 * 2. Multiple BackStack
 * 3. BottomNavigation Visibility
 * 4. NetworkMonitor(isOffline / isOnline)
 * 5. ...
 */
@Stable
class KeyLinkAppState @OptIn(ExperimentalMaterialApi::class) constructor(
    val navController: NavHostController,
    val scaffoldState: ScaffoldState,
    val modalBottomSheetState: ModalBottomSheetState,
    val coroutineScope: CoroutineScope
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().toList()

    fun showSnackbar(message: String, duration: SnackbarDuration = SnackbarDuration.Short) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar(
                message = message,
                duration = duration
            )
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun controlBottomSheet() {
        coroutineScope.launch {
            if (modalBottomSheetState.isVisible) modalBottomSheetState.hide()
            else modalBottomSheetState.show()
        }
    }

    @Composable
    fun isBottomBarVisible(): Boolean {
        return when (currentDestination?.route) {
            KeyLinkNavigationRoute.HomeGraph.HomeRoute.route,
            KeyLinkNavigationRoute.ChatRoomGraph.ChatRoomRoute.route -> true
            else -> false
        }
    }

    /**
     * Home, Chat Route로 다시 돌아와야 하는 경우 호출하는 메서드
     * singleTop으로 동작 및 backstack 유지
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(id = navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
        when (topLevelDestination) {
            TopLevelDestination.HOME -> navController.navigateToHome(topLevelNavOptions)
            TopLevelDestination.SIGNAL -> navController.navigateToSignal(topLevelNavOptions)
            TopLevelDestination.CHAT_ROOM -> navController.navigateToChatRoom(topLevelNavOptions)
        }
    }
}