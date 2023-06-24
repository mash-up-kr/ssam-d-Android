package com.mashup.presentation.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.ui.theme.*

@Composable
fun KeyLinkButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    buttonHeight: ButtonHeight = ButtonHeight.Large,
    backgroundColor: Color = Blurple,
    contentColor: Color = White,
    disabledBackgroundColor: Color = Gray02,
    disabledContentColor: Color = Gray06,
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 16.sp,
    enable: Boolean = true
) {
    Button(
        modifier = modifier.height(buttonHeight.height.dp),
        onClick = { onClick.invoke() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = disabledBackgroundColor,
            disabledContentColor = disabledContentColor
        ),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        enabled = enable
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = fontWeight,
            maxLines = 1
        )
    }
}

@Composable
fun KeyLinkRoundButton(
    text: String,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = Modifier.wrapContentSize(),
        onClick = { onClick.invoke() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Blurple,
            contentColor = White,
            disabledBackgroundColor = Gray02,
            disabledContentColor = Gray06
        ),
        shape = RoundedCornerShape(24.dp),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            maxLines = 1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewKeyLinkButton() {
    SsamDTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            KeyLinkButton(modifier = Modifier.fillMaxWidth(), text = "높이가 Large")
            KeyLinkButton(
                modifier = Modifier.fillMaxWidth(),
                buttonHeight = ButtonHeight.Medium,
                text = "높이가 Medium",
            )
            KeyLinkButton(
                modifier = Modifier.fillMaxWidth(),
                enable = false,
                text = "disable에 높이가 Large"
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                KeyLinkButton(text = "왼쪽", enable = false)
                KeyLinkButton(text = "오른쪽", enable = true)
            }
        }
    }
}

enum class ButtonHeight(val height: Int) {
    Large(52), Medium(44)
}