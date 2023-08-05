package com.mashup.presentation.feature.detail.crash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/08/05
 */
fun NavController.navigateToCrashDetail(
    crashId: Long,
    navOptions: NavOptions? = null
) {
    navigate(
        route = KeyLinkNavigationRoute.SignalZoneGraph.CrashDetailRoute.route
            .replace("{crashId}", "$crashId"),
        navOptions = navOptions
    )
}
