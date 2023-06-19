package com.mashup.presentation.common.extension

import android.content.Context
import android.view.LayoutInflater
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mashup.presentation.databinding.BadgeBottomNavigationSignalBinding

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/19
 */
internal fun BottomNavigationView.addBadge(
    context: Context,
    badgeValue: Int
) {
    removeBadge() // overdraw 방지
    val bottomMenu = getChildAt(0) as BottomNavigationMenuView
    val itemView = bottomMenu.getChildAt(2) as BottomNavigationItemView

    val badgeBinding =
        BadgeBottomNavigationSignalBinding.inflate(LayoutInflater.from(context), bottomMenu, false)
    badgeBinding.tvBadge.text = handleBadgeValue(badgeValue)

    itemView.addView(badgeBinding.root)
}

internal fun BottomNavigationView.removeBadge() {
    val bottomMenu = getChildAt(0) as BottomNavigationMenuView
    val itemView = bottomMenu.getChildAt(2) as BottomNavigationItemView

    if (itemView.childCount == 3) {  // badge까지 추가되면 3
        itemView.removeViewAt(2)
    }
}

private fun handleBadgeValue(value: Int): String {
    return when(value) {
        in Int.MIN_VALUE .. 0 -> ""
        in 1 .. 998 -> "$value"
        else -> "999+"
    }
}