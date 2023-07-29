package com.mashup.presentation.feature.detail.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/09
 */

fun NavController.navigateToChatRoomDetail(roomId: Long, navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.ChatRoomGraph.ChatRoomDetailRoute.route.replace(
            "{roomId}",
            "$roomId"
        ),
        navOptions = navOptions
    )
}