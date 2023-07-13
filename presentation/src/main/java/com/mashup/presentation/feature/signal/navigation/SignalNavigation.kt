package com.mashup.presentation.feature.signal.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.presentation.feature.home.navigation.navigateToHome
import com.mashup.presentation.feature.signal.compose.SignalCompleteRoute
import com.mashup.presentation.feature.signal.compose.SignalContentRoute
import com.mashup.presentation.feature.signal.compose.SignalKeywordRoute
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
fun NavController.navigateToSignal(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.SignalGraph.route,
        navOptions = navOptions
    )
}

fun NavController.navigateToSignalKeyword(content: String, navOptions: NavOptions? = null) {
    navigate(
        route = "${KeyLinkNavigationRoute.SignalGraph.SignalKeywordRoute.route}/${content}",
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

        composable(
            route = "${KeyLinkNavigationRoute.SignalGraph.SignalKeywordRoute.route}/{content}",
            arguments = listOf(
                navArgument("content") {
                    type = NavType.StringType
                    nullable = false
                }
            )
        ) { backStackEntry ->
            SignalKeywordRoute(
                content = backStackEntry.arguments?.getString("content") ?: "",
                onBackClick = onBackClick,
                onSendClick = navController::navigateToSignalComplete
            )
        }

        composable(route = KeyLinkNavigationRoute.SignalGraph.SignalCompleteRoute.route) {
            SignalCompleteRoute(
                onCloseClick = {
                    navController.navigateToHome(
                        navOptions {
                            popUpTo(KeyLinkNavigationRoute.SignalGraph.SignalContentRoute.route) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    )
                }
            )
        }
    }
}
