package com.mashup.presentation.feature.reply.chat.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.mashup.presentation.navigation.KeyLinkNavigationRoute

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/15
 */
fun NavController.navigateToChatReply(roomId: Long, navOptions: NavOptions? = null) {
    navigate(
        route = KeyLinkNavigationRoute.ChatGraph.ChatReplyRoute.route.replace(
            "{roomId}",
            "$roomId"
        ),
        navOptions = navOptions
    )
}