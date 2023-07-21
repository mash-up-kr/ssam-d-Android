package com.mashup.presentation.navigation

import com.mashup.presentation.R

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/04
 */
enum class TopLevelDestination(
    val selectedIconId: Int,
    val unselectedIconId: Int,
    val iconTextId: Int,
    val textLabelId: Int
) {
    HOME(
        selectedIconId = R.drawable.ic_home_fill_32,
        unselectedIconId = R.drawable.ic_home_fill_32,
        iconTextId = R.string.navigation_home,
        textLabelId = R.string.navigation_home,
    ),
    SIGNAL(
        selectedIconId = R.drawable.ic_signal_fill_32,
        unselectedIconId = R.drawable.ic_signal_fill_32,
        iconTextId = R.string.navigation_signal,
        textLabelId = R.string.navigation_signal,
    ),
    CHAT_ROOM(
        selectedIconId = R.drawable.ic_chat_fill_32,
        unselectedIconId = R.drawable.ic_chat_fill_32,
        iconTextId = R.string.navigation_chat,
        textLabelId = R.string.navigation_chat,
    ),
}