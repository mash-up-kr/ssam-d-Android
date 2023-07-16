package com.mashup.presentation.feature.reply.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/15
 */
fun NavController.navigateToReplyRoute(navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.ChatGraph.ReplyRoute.route,
        navOptions = navOptions
    )
}