package com.mashup.presentation.detail.chat

import androidx.compose.ui.graphics.Color

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */
enum class MessageBackgroundColor(
    private val startColor: Color,
    private val endColor: Color,
) {
    PURPLE(Color(0XFF7E4FCB), Color(0XFF707AEF)),
    ORANGE(Color(0XFF663629), Color(0XFFFF6543)),
    MINT(Color(0XFF288160), Color(0XFF16B67B)),
    PINK(Color(0XFFFF5BAC), Color(0XFFFF77BA)),
    GREEN(Color(0XFF508448), Color(0XFF9EBB6E));

    fun getGradientColors(): List<Color> {
        return listOf(startColor, endColor)
    }
}