package com.mashup.presentation.ui.common

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/11
 */
private val trackWidth = 48.dp
private val trackHeight = 28.dp
private val thumbRadius = 12.dp
private val thumbPadding = 2.dp

/**
 * [KeyLinkSwitch]
 *
 * animatePosition : Thumb를 이동시키기 위해 주어진 trackWidth, thumbRadius, thumbPadding으로 position을 계산하여 State로 반환합니다. </p>
 *
 * trackBackgroundColor, thumbBackgroundColor : isChecked 여부에 따라 달라지는 Color값들을 State로 반환합니다.
 *
 */
@Composable
fun KeyLinkSwitch(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val trackBackgroundColor by animateColorAsState(targetValue = if (isChecked) Blurple else Gray04)
    val thumbBackgroundColor by animateColorAsState(targetValue = if (isChecked) Mint else White)

    val animatePosition = animateFloatAsState(
        targetValue = with(LocalDensity.current) {
            if (isChecked) {
                (trackWidth - thumbRadius - thumbPadding).toPx()
            } else {
                (thumbRadius + thumbPadding).toPx()
            }
        }
    )

    Canvas(
        modifier = modifier
            .size(width = trackWidth, height = trackHeight)
            .toggleable(
                value = isChecked,
                onValueChange = onCheckedChange,
                enabled = true,
                role = Role.Switch,
                interactionSource = MutableInteractionSource(),
                indication = null
            )
    ) {
        // Track
        drawRoundRect(
            color = trackBackgroundColor,
            cornerRadius = CornerRadius(x = 14.dp.toPx(), y = 14.dp.toPx()),
        )

        // Thumb
        drawCircle(
            color = thumbBackgroundColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2  // trackHeight의 반만큼 offset 적용
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun KeyLinkSwitchCheckedPreview() {
    SsamDTheme {
        KeyLinkSwitch(
            isChecked = true,
            onCheckedChange = { }
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun KeyLinkSwitchUnCheckedPreview() {
    SsamDTheme {
        KeyLinkSwitch(
            isChecked = false,
            onCheckedChange = { }
        )
    }
}
