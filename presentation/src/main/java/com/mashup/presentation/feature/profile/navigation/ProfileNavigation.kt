package com.mashup.presentation.feature.profile.navigation

import androidx.compose.material.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.presentation.feature.profile.compose.ProfileRoute
import com.mashup.presentation.navigation.KeyLinkNavigationRoute
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/10
 */
fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.ProfileGraph.route,
        navOptions = navOptions
    )
}

fun NavController.navigateToNavigationRoute(
    route: String,
    navOptions: NavOptions? = null
) {
    navigate(
        route = route,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.profileGraph(
    navController: NavController,
    onBackClick: () -> Unit,
) {
    navigation(
        route = KeyLinkNavigationRoute.ProfileGraph.route,
        startDestination = KeyLinkNavigationRoute.ProfileGraph.ProfileRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.ProfileGraph.ProfileRoute.route) {
            ProfileRoute(
                onBackClick = onBackClick,
                onNavigateClick = navController::navigateToNavigationRoute
            )
        }
        composable(route = KeyLinkNavigationRoute.ProfileGraph.SendSignalRoute.route) {
            // SendSignalRoute
            Text(text = "SendSignal", color = White)
        }
        composable(route = KeyLinkNavigationRoute.ProfileGraph.TermsOfServiceRoute.route) {
            // TermsOfServiceRoute
            Text(text = "TOS", color = White)
        }
        composable(route = KeyLinkNavigationRoute.ProfileGraph.PrivacyPolicyRoute.route) {
            // PrivacyPolicyRoute
            Text(text = "Privacy", color = White)
        }
        composable(route = KeyLinkNavigationRoute.ProfileGraph.OpenSourceRoute.route) {
            // OpenSourceRoute
            Text(text = "OpenSource", color = White)
        }
    }
}