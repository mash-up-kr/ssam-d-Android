package com.mashup.presentation.common.extension

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/21
 */

internal fun Modifier.shimmerEffect(delay: Int = 0): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1300,
                delayMillis = delay
            )
        )
    )

    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Gray04,
                Gray03,
                Gray02,
                Gray03,
                Gray04,
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        ),
    ).onGloballyPositioned {
        size = it.size
    }
}

internal fun Modifier.drawBorder(draw: Boolean) = this.then(
    if (draw) {
        Modifier.border(
            width = 1.dp,
            brush = Brush.linearGradient(
                colors = listOf(Purple, Mint)
            ),
            shape = RoundedCornerShape(10.dp)
        )
    } else Modifier
)