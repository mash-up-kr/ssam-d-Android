package com.mashup.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkButton
import com.mashup.presentation.ui.common.KeyLinkMintText

@Composable
fun NicknameScreen(onNextButtonClicked: () -> Unit){
    Box {
        LoginBackground()

        LoginContainer(modifier = Modifier.padding(top = 233.dp, bottom = 12.dp, start = 20.dp, end = 20.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                KeyLinkMintText(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                    text = stringResource(R.string.login_nickname)
                )
            }

            KeyLinkButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.login_next_btn),
                onClick = { onNextButtonClicked() },
                enable = true
            )
        }
    }
}