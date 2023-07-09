package com.mashup.presentation.feature.signal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.presentation.feature.signal.compose.SignalContentRoute
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
fun NavController.navigateToSignal(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.SignalGraph.SignalContentRoute.route,
        navOptions = navOptions
    )
}
fun NavController.navigateToSignalKeyword(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.SignalGraph.SignalKeywordRoute.route,
        navOptions = navOptions
    )
}

fun NavController.navigateToSignalComplete(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.SignalGraph.SignalCompleteRoute.route,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.signalGraph(
    navController: NavController,
    onBackClick: () -> Unit,
) {
    navigation(
        route = KeyLinkNavigationRoute.SignalGraph.route,
        startDestination = KeyLinkNavigationRoute.SignalGraph.SignalContentRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.SignalGraph.SignalContentRoute.route) {
            SignalContentRoute(
                onBackClick = onBackClick,
                onNextClick = navController::navigateToSignalKeyword
            )
        }

        composable(route = KeyLinkNavigationRoute.SignalGraph.SignalKeywordRoute.route) {
            // SignalKeywordRoute
        }

        composable(route = KeyLinkNavigationRoute.SignalGraph.SignalCompleteRoute.route) {
            // SignalCompleteRoute
        }
    }
}
