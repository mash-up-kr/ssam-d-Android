package com.mashup.presentation.ui.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.mashup.presentation.ui.theme.Mint
import com.mashup.presentation.ui.theme.Typography

@Composable
fun KeyLinkMintText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = Typography.h4,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        color = Mint,
        style = textStyle.copy(shadow = Shadow(color = Mint.copy(alpha = 0.7f), blurRadius = 10f))
    )
}
