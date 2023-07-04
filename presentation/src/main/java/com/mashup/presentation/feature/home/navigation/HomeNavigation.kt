package com.mashup.presentation.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
fun NavController.navigateToGuideRoute(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.HomeGraph.GuideRoute.route,
        navOptions = navOptions
    )
}

fun NavController.navigateToEditKeywordRoute(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.HomeGraph.SubscribeKeywordRoute.route,
        navOptions = navOptions
    )
}

fun NavController.navigateToProfileRoute(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.HomeGraph.ProfileRoute.route,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeGraph() {
    navigation(
        route = KeyLinkNavigationRoute.HomeGraph.route,
        startDestination = KeyLinkNavigationRoute.HomeGraph.HomeRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.HomeGraph.HomeRoute.route) {
            // HomeRoute
        }
        composable(route = KeyLinkNavigationRoute.HomeGraph.GuideRoute.route) {
            // GuideRoute
        }
        composable(route = KeyLinkNavigationRoute.HomeGraph.SubscribeKeywordRoute.route) {
            // SubscribeKeywordRoute
        }
        composable(route = KeyLinkNavigationRoute.HomeGraph.ProfileRoute.route) {
            // ProfileRoute
        }
    }

}