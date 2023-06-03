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
import androidx.compose.ui.tooling.preview.Preview
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
    fontSize: Int,
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
        placeholder = { Text(text = hint, fontSize = fontSize.sp, color = Gray04) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onClickDone.invoke() },
        ),
        textStyle = TextStyle(
            fontSize = fontSize.sp,
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
            fontSize = 32,
            maxLength = 10,
        )
    }
}