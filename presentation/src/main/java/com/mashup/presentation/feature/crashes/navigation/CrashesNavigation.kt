package com.mashup.presentation.feature.crashes.navigation

import androidx.compose.material.SnackbarDuration
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.presentation.feature.crashes.compose.CrashesRoute
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

fun NavController.navigateToCrashes(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.CrashesGraph.route,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.crashesGraph(
    navController: NavController,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    onBackClick: () -> Unit
) {
    navigation(
        route = KeyLinkNavigationRoute.CrashesGraph.route,
        startDestination = KeyLinkNavigationRoute.CrashesGraph.CrashesRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.CrashesGraph.CrashesRoute.route) {
            CrashesRoute()
        }
    }
}
