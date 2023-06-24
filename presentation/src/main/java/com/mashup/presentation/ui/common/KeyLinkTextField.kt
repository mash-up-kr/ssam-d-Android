package com.mashup.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.ui.theme.*

@Composable
fun KeyLinkTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    hintAlign: TextAlign = TextAlign.Center,
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
                textAlign = hintAlign,
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

@Composable
fun KeyLinkOnBoardingTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    fontSize: TextUnit,
    onClickDone: () -> Unit,
    maxLength: Int = 0,
    minLength: Int = 0
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        // 항상 커서 보일 수 있도록
        focusRequester.requestFocus()
    }

    Box(modifier = modifier
        .padding(top = 22.dp)
        .fillMaxWidth()
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                // 띄어쓰기 제거
                val noSpaceText = it.replace("\\s".toRegex(), "")
                if (maxLength == 0 || noSpaceText.length <= maxLength) {
                    onValueChange(noSpaceText)
                }
            },
            textStyle = TextStyle(
                fontSize = fontSize,
                color = White
            ),
            modifier = modifier
                .focusRequester(focusRequester),
            cursorBrush = SolidColor(Mint),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (value.length >= minLength) {
                        onClickDone()
                    }
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

@Composable
fun KeyLinkBoxTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    onClickDone: () -> Unit = {},
    fontSize: TextUnit,
    validationState: ValidationState
) {
    Column(
        modifier = modifier
    ) {
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.Transparent)
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(10.dp),
                    color = when (validationState) {
                        ValidationState.EMPTY, ValidationState.SUCCESS -> Gray10
                        else -> Red
                    }
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 15.dp
                ),
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
            cursorBrush = SolidColor(Mint),
            singleLine = true,
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
            },
        )

        Text(
            text = setDescriptionText(validationState).first,
            style = TextStyle(
                color = setDescriptionText(validationState).second,
                fontSize = 12.sp
            ),
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

private fun setDescriptionText(state: ValidationState): Pair<String, Color> {
    return when (state) {
        ValidationState.EMPTY -> Pair("10자 이내로 가능해요", Gray07)
        ValidationState.SUCCESS -> Pair("당신은 닉네임 짓기 장인!", Gray10)
        ValidationState.FAILED -> Pair("삐-익! 중복된 닉네임이에요!", Red)
    }
}

enum class ValidationState {
    EMPTY, SUCCESS, FAILED
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

@Preview(showBackground = true)
@Composable
fun PreviewKeyLinkOnBoardingTextField() {
    Column(modifier = Modifier.fillMaxWidth()) {
        var value by remember { mutableStateOf("") }

        KeyLinkOnBoardingTextField(
            value = value,
            onValueChange = { value = it },
            hint = "ㅁㄴㅇㄹㅁㄴㅇㄹ",
            fontSize = 32.sp,
            onClickDone = { /*TODO*/ })
    }
}

private fun checkValidation(
    text: String,
    expectedString: String,
    onValidate: (ValidationState) -> Unit,
) {
    if (text.isEmpty()) onValidate.invoke(ValidationState.EMPTY)
    else if (text == expectedString) onValidate.invoke(ValidationState.SUCCESS)
    else onValidate.invoke(ValidationState.FAILED)
}

@Preview(showBackground = true)
@Composable
fun PreviewKeyLinkBoxTextField() {
    SsamDTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            var value by remember { mutableStateOf("") }
            var validation by remember { mutableStateOf(ValidationState.EMPTY) }
            val expectedText = "올바른 닉네임"

            KeyLinkBoxTextField(
                value = value,
                onValueChange = {
                    value = it
                    checkValidation(value, expectedText) { validationState ->
                        validation = validationState
                    }
                },
                hint = "확신의 일론머스크",
                maxLength = 10,
                fontSize = 32.sp,
                validationState = validation
            )
        }
    }
}