package com.mashup.presentation.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.ui.theme.*

@Composable
fun KeyLinkButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    buttonHeight: ButtonHeight = ButtonHeight.Large,
    text: String,
    enable: Boolean = true
) {
    Button(
        modifier = modifier.height(buttonHeight.height.dp),
        onClick = { onClick.invoke() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Blurple,
            contentColor = White,
            disabledBackgroundColor = Gray02,
            disabledContentColor = Gray06
        ),
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        enabled = enable
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            maxLines = 1
        )
    }
}

@Composable
fun KeyLinkPurpleButton(
    text: String,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = Modifier.height(55.dp),
        onClick = { onClick.invoke() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Purple,
            contentColor = White,
            disabledBackgroundColor = Gray02,
            disabledContentColor = Gray06
        ),
        shape = RoundedCornerShape(6.dp),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
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