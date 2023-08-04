package com.mashup.presentation.feature.profile.navigation

import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.mashup.presentation.feature.profile.compose.ProfileRoute
import com.mashup.presentation.feature.profile.policy.PrivacyPolicyRoute
import com.mashup.presentation.feature.profile.tos.TermsOfServiceRoute
import com.mashup.presentation.feature.profile.withdrawal.WithdrawalRoute
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
    userId: Long,
    navOptions: NavOptions? = null
) {
    navigate(
        route = route.replace("{userId}", "$userId"),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.profileGraph(
    navController: NavController,
    onShowSnackbar: (String, SnackbarDuration) -> Unit,
    onBackClick: () -> Unit,
) {
    navigation(
        route = KeyLinkNavigationRoute.ProfileGraph.route,
        startDestination = KeyLinkNavigationRoute.ProfileGraph.ProfileRoute.route
    ) {
        composable(route = KeyLinkNavigationRoute.ProfileGraph.ProfileRoute.route) {
            ProfileRoute(
                onBackClick = onBackClick,
                onNavigateClick = { route, userId ->
                    navController.navigateToNavigationRoute(route, userId)
                }
            )
        }
        composable(route = KeyLinkNavigationRoute.ProfileGraph.TermsOfServiceRoute.route) {
            TermsOfServiceRoute(onBackClick = onBackClick)
        }
        composable(route = KeyLinkNavigationRoute.ProfileGraph.PrivacyPolicyRoute.route) {
            PrivacyPolicyRoute(onBackClick = onBackClick)
        }
        composable(
            route = KeyLinkNavigationRoute.ProfileGraph.WithdrawalRoute.route,
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            val userId = entry.arguments?.getString("userId")?.toLong() ?: -1
            WithdrawalRoute(
                userId = userId,
                onBackClick = onBackClick,
                onShowSnackbar = onShowSnackbar
            )
        }
    }
}