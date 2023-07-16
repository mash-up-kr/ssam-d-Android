package com.mashup.presentation

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.mashup.presentation.feature.chat.navigation.navigateToChat
import com.mashup.presentation.feature.home.navigation.navigateToHome
import com.mashup.presentation.feature.signal.navigation.navigateToSignal
import com.mashup.presentation.navigation.KeyLinkNavigationRoute
import com.mashup.presentation.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */

@Composable
fun rememberKeyLinkAppState(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(
        snackbarHostState = remember { SnackbarHostState() }
    ),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): KeyLinkAppState {
    return remember(navController, scaffoldState, coroutineScope) {
        KeyLinkAppState(
            navController,
            scaffoldState,
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
class KeyLinkAppState(
    val navController: NavHostController,
    val scaffoldState: ScaffoldState,
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
    @Composable
    fun isBottomBarVisible(): Boolean {
        return when (currentDestination?.route) {
            KeyLinkNavigationRoute.HomeGraph.HomeRoute.route,
            KeyLinkNavigationRoute.ChatGraph.ChatRoute.route -> true
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
            TopLevelDestination.CHAT -> navController.navigateToChat(topLevelNavOptions)
        }
    }
}