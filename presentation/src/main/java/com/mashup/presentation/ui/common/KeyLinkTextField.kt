package com.mashup.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.mashup.presentation.ui.theme.Gray04
import com.mashup.presentation.ui.theme.Mint
import com.mashup.presentation.ui.theme.Red
import com.mashup.presentation.ui.theme.White

@Composable
fun KeyLinkTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    onClickDone: () -> Unit,
    fontSize: TextUnit,
    maxLength: Int = 0
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            if (maxLength == 0 || it.length <= maxLength) {
                onValueChange(it)
            }
        },
        placeholder = {
            Text(
                textAlign = TextAlign.Center,
                text = hint,
                fontSize = fontSize,
                color = Gray04
            )
        },
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
            onValueChange = { nickname = it },
            hint = "닉네임 입력",
            fontSize = 32.sp,
            maxLength = 10,
            onClickDone = {}
        )
    }
}

@Composable
fun KeyLinkOnBoardingTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    fontSize: TextUnit,
    onClickDone: () -> Unit,
    maxLength: Int = 0
) {
    Box(modifier = modifier.padding(top = 22.dp)) {
        BasicTextField(
            value = value,
            onValueChange = {
                if (maxLength == 0 || it.length <= maxLength) {
                    onValueChange(it)
                }
            },
            textStyle = TextStyle(
                fontSize = fontSize,
                color = White
            ),
            modifier = modifier
                .width(IntrinsicSize.Max),
            cursorBrush = SolidColor(Mint),
            keyboardActions = KeyboardActions(
                onDone = {
                    onClickDone()
                }
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = fontSize
                        ),
                        modifier = Modifier
                            .alpha(ContentAlpha.medium)
                    )
                }
                innerTextField()
            }
        )
    }

}