package com.mashup.presentation.chat.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkRoundButton
import com.mashup.presentation.ui.theme.Body1
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/27
 */
@Composable
fun EmptyChatScreen(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_chat_empty),
            contentDescription = stringResource(R.string.content_description_empty_chat)
        )

        Text(
            text = stringResource(id = R.string.chat_empty_first_description),
            style = Body1,
            color = White
        )

        Text(
            text = stringResource(id = R.string.chat_empty_second_description),
            style = Body1,
            color = White
        )

        KeyLinkRoundButton(
            text = stringResource(id = R.string.button_send_signal),
            onClick = { onButtonClick() },
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}