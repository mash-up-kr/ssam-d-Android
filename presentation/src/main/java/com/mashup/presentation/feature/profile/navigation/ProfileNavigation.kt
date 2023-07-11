package com.mashup.presentation.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mashup.presentation.feature.profile.compose.ProfileRoute
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/10
 */
fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.HomeGraph.ProfileRoute.route,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.profileGraph(
    onBackClick: () -> Unit,
) {
    navigation(
        route = KeyLinkNavigationRoute.ProfileGraph.route,
        startDestination = KeyLinkNavigationRoute.ProfileGraph.ProfileRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.ProfileGraph.ProfileRoute.route) {
            ProfileRoute(
                onBackClick = onBackClick
            )
        }
        composable(route = KeyLinkNavigationRoute.ProfileGraph.SendSignalRoute.route) {
            // SendSignalRoute
        }
        composable(route = KeyLinkNavigationRoute.ProfileGraph.TermsOfServiceRoute.route) {
            // TermsOfServiceRoute
        }
        composable(route = KeyLinkNavigationRoute.ProfileGraph.PrivacyPolicyRoute.route) {
            // PrivacyPolicyRoute
        }
        composable(route = KeyLinkNavigationRoute.ProfileGraph.OpenSourceRoute.route) {
            // OpenSourceRoute
        }
    }
}