package com.mashup.presentation.feature.home.navigation

import androidx.compose.material.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mashup.presentation.feature.guide.GuideRoute
import com.mashup.presentation.feature.home.HomeRoute
import com.mashup.presentation.feature.home.HomeViewModel
import com.mashup.presentation.feature.profile.navigation.navigateToProfile
import com.mashup.presentation.feature.signal.navigation.navigateToSignal
import com.mashup.presentation.feature.subscribe.SubscribeRoute
import com.mashup.presentation.feature.subscribe.navigation.navigateToSubscribeKeywordRoute
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
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    onGuideClick: () -> Unit,
    onBackClick: () -> Unit,
    nestedSignalGraph: NavGraphBuilder.() -> Unit,
    nestedProfileGraph: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = KeyLinkNavigationRoute.HomeGraph.route,
        startDestination = KeyLinkNavigationRoute.HomeGraph.HomeRoute.route,
    ) {
        composable(route = KeyLinkNavigationRoute.HomeGraph.HomeRoute.route) { backStackEntry ->
            val homeViewModel = backStackEntry.sharedViewModel<HomeViewModel>(navController)
            HomeRoute(
                onKeywordContainerClick = navController::navigateToSubscribeKeywordRoute,
                onGuideClick = onGuideClick,
                onProfileMenuClick = navController::navigateToProfile,
                onReceivedSignalClick = {},
                homeViewModel = homeViewModel
            )
        }
        composable(route = KeyLinkNavigationRoute.HomeGraph.GuideRoute.route) {
            GuideRoute(
                onBackClick = onBackClick,
                onButtonClick = navController::navigateToSignal
            )
        }
        composable(route = KeyLinkNavigationRoute.HomeGraph.SubscribeKeywordRoute.route) { backStackEntry ->
            val homeViewModel = backStackEntry.sharedViewModel<HomeViewModel>(navController)

            SubscribeRoute(
                onBackClick = onBackClick,
                onSaveButtonClick = onBackClick,
                onShowSnackbar = onShowSnackbar,
                homeViewModel = homeViewModel
            )
        }
        nestedSignalGraph()
        nestedProfileGraph()
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)
}