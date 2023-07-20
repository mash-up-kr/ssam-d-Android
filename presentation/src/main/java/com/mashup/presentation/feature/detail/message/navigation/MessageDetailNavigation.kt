package com.mashup.presentation.feature.detail.message.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/09
 */
fun NavController.navigateToMessageDetail(
    roomId: Long,
    chatId: Long,
    navOptions: NavOptions? = null
) {
    navigate(
        route = KeyLinkNavigationRoute.ChatGraph.MessageDetailRoute.route
            .replace("{roomId}", "$roomId")
            .replace("{chatId}", "$chatId"),
        navOptions = navOptions
    )
}