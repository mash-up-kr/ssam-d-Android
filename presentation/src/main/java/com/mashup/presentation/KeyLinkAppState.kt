package com.mashup.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */

@Composable
fun rememberKeyLinkAppState(
    navController: NavHostController = rememberNavController()
): KeyLinkAppState {
    return remember(navController) {
        KeyLinkAppState(navController)
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
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination


    @Composable
    fun isBottomBarVisible(): Boolean {
        return true
//        return when(currentDestination?.route) {
//
//        }
    }
}