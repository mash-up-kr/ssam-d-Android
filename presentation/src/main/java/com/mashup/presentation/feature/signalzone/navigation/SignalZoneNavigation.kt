package com.mashup.presentation.feature.signalzone.navigation

import androidx.compose.material.SnackbarDuration
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.presentation.feature.signalzone.compose.SignalZoneRoute
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

fun NavController.navigateToSignalZone(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.SignalZoneGraph.route,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.signalZoneGraph(
    navController: NavController,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    onBackClick: () -> Unit
) {
    navigation(
        route = KeyLinkNavigationRoute.SignalZoneGraph.route,
        startDestination = KeyLinkNavigationRoute.SignalZoneGraph.SignalZoneRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.SignalZoneGraph.SignalZoneRoute.route) {
            SignalZoneRoute()
        }
    }
}
