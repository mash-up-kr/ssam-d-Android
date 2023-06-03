package com.mashup.presentation.ui.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mashup.presentation.ui.theme.Mint

@Composable
fun KeyLinkMintText(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            color = Mint.copy(alpha = 0.7f),
            shadow = Shadow(
                color = Mint,
                blurRadius = 35f
            )
        )
    )
}
