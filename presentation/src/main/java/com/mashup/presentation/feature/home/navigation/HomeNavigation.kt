package com.mashup.presentation.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mashup.presentation.feature.guide.GuideRoute
import com.mashup.presentation.feature.home.HomeRoute
import com.mashup.presentation.feature.profile.navigation.navigateToProfile
import com.mashup.presentation.feature.signal.navigation.navigateToSignal
import com.mashup.presentation.feature.subscribe.SubscribeRoute
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.HomeGraph.route,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeGraph(
    navController: NavController,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    onSubscribeKeywordClick: () -> Unit,
    onGuideClick: () -> Unit,
    onBackClick: () -> Unit,
    nestedSignalGraph: NavGraphBuilder.() -> Unit,
    nestedProfileGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = KeyLinkNavigationRoute.HomeGraph.route,
        startDestination = KeyLinkNavigationRoute.HomeGraph.HomeRoute.route,
    ) {
        composable(route = KeyLinkNavigationRoute.HomeGraph.HomeRoute.route) {
            HomeRoute(
                onSubscribeKeywordClick = onSubscribeKeywordClick,
                onGuideClick = onGuideClick,
                onProfileClick = navController::navigateToProfile
            )
        }
        composable(route = KeyLinkNavigationRoute.HomeGraph.GuideRoute.route) {
            GuideRoute(
                onBackClick = onBackClick,
                onButtonClick = navController::navigateToSignal
            )
        }
        composable(route = KeyLinkNavigationRoute.HomeGraph.SubscribeKeywordRoute.route) {
            SubscribeRoute(
                onBackClick = onBackClick,
                onSaveButtonClick = onBackClick,
                onShowSnackbar = onShowSnackbar
            )
        }
        nestedSignalGraph()
        nestedProfileGraph()
    }
}