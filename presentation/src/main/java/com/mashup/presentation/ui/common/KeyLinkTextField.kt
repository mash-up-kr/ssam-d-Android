package com.mashup.presentation.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.mashup.presentation.ui.theme.Gray04
import com.mashup.presentation.ui.theme.Mint
import com.mashup.presentation.ui.theme.White

@Composable
fun KeyLinkTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChanged: (String) -> Unit = {},
    hint: String,
    onClickDone: () -> Unit = {},
    fontSize: TextUnit,
    maxLength: Int = 0
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            if (maxLength > 0 && it.length <= maxLength) {
                onValueChanged(it)
            }
        },
        placeholder = { Text(textAlign = TextAlign.Center, text = hint, fontSize = fontSize, color = Gray04) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onClickDone.invoke() },
        ),
        textStyle = TextStyle(
            fontSize = fontSize,
            color = White
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Mint,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewKeyLinkTextField() {
    Column(modifier = Modifier.fillMaxWidth()) {
        var nickname by remember { mutableStateOf("") }

        KeyLinkTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nickname,
            onValueChanged = { nickname = it },
            hint = "닉네임 입력",
            fontSize = 32.sp,
            maxLength = 10,
        )
    }
}
@Composable
fun KeyLinkNicknameField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    maxLength: Int = 0,
    fontSize: TextUnit,
    onClickDone: () -> Unit,
    hint: String
) {
    var isHintVisible by remember { mutableStateOf(true) }
    val nickname = remember { mutableStateOf("") }

    Box(modifier = modifier) {
        BasicTextField(
            value = nickname.value,
            onValueChange = {
                if (maxLength > 0 && it.length <= maxLength) {
                    nickname.value = it
                    onValueChange(it)
                }
            },
            textStyle = TextStyle(
                textAlign = TextAlign.Center,
                fontSize = fontSize,
                color = White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused && nickname.value.isBlank()) {
                        nickname.value = ""
                        isHintVisible = true
                    } else {
                        isHintVisible = false
                    }
                },
            cursorBrush = SolidColor(Mint),
            keyboardActions = KeyboardActions(
                onDone = {
                    onClickDone()
                }
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            decorationBox = { innerTextField ->
                if (isHintVisible) {
                    Text(
                        text = hint,
                        style = TextStyle(
                            color = Color.Gray,
                            textAlign = TextAlign.Center,
                            fontSize = fontSize
                        ),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .fillMaxWidth()
                            .alpha(ContentAlpha.medium)
                    )
                }
                innerTextField()
            }
        )
    }
}
