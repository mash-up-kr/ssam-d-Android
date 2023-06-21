package com.mashup.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.*

@Composable
fun NicknameScreen(onNextButtonClicked: (String) -> Unit){
    var nickname by remember { mutableStateOf("") }
    var validation by remember { mutableStateOf(ValidationState.EMPTY) }
    val expectedText = "올바른 닉네임"

    Box {
        LoginBackground()

        LoginContainer(modifier = Modifier.padding(top = 185.dp, bottom = 12.dp, start = 20.dp, end = 20.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                KeyLinkMintText(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                    text = stringResource(R.string.login_nickname),
                    textAlign = TextAlign.Left
                )

                KeyLinkBoxTextField(
                    value = nickname,
                    onValueChange = { value ->
                        nickname = value
                        checkValidation(nickname, expectedText) { validationState ->
                            validation = validationState
                        }
                    },
                    hint = stringResource(R.string.login_nickname_hint),
                    maxLength = 10,
                    fontSize = 32.sp,
                    validationState = validation
                )
            }

            KeyLinkButton(
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
                text = stringResource(R.string.login_next_btn),
                onClick = { onNextButtonClicked(nickname) },
                enable = validation == ValidationState.SUCCESS
            )
        }
    }
}