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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/21
 */

/* ShimmerEffect를 적용하기 위해 사용되는 Modifier */
internal fun Modifier.shimmerEffect(delay: Int = 0): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),  // 애니메이션이 적용될 마이너스 x좌표부터 시작됨 (외부)
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(  // 주어진 값 동안에 initialValue와 targetValue 간에 애니메이션 적용
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
    ).onGloballyPositioned {  // 반환된 size를 remember시키기 위해 필요
        size = it.size
    }
}

/**
 * innerRect: 안쪽 사각형(drop shadow가 그려질 대상)
 * outerRect: 바깥쪽 사각형(drop shadow)
 * 두 사각형을 op(PathOperation.Difference)를 통해 차이가 있는 곳만 그립니다.
 * 즉, 안쪽 사각형을 벗어나는 곳만 drop shadow인 outerRect을 그려줍니다.
 */
internal fun Modifier.drawColoredShadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = this.drawBehind {
    val transparentColor = color.copy(alpha = 0.0f).toArgb()
    val shadowColor = color.copy(alpha = alpha).toArgb()
    this.drawIntoCanvas {
        val innerRect = RoundRect(
            0f, 0f, this.size.width - 2f, this.size.height - 2f,
            CornerRadius(borderRadius.toPx(), borderRadius.toPx())
        )

        val outerRect = RoundRect(
            offsetX.toPx(),
            offsetY.toPx(),
            (this.size.width + offsetX.toPx()),
            (this.size.height + offsetY.toPx()),
            CornerRadius(borderRadius.toPx(), borderRadius.toPx())
        )
        val outerPath = Path().apply { addRoundRect(outerRect) }
        val innerPath = Path().apply { addRoundRect(innerRect) }

        val resultPath = Path().apply {
            op(outerPath, innerPath, PathOperation.Difference)
        }
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawPath(resultPath, paint)
    }
}
