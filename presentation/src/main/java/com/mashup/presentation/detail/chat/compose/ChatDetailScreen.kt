package com.mashup.presentation.detail.chat.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */
@Composable
fun ChatDetailScreen(
    modifier: Modifier = Modifier,
    navigateUp: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        backgroundColor = Black,
        topBar = {
            KeyLinkToolbar(
                menuAction = {
                    IconButton(onClick = { navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_more_horizontal_24),
                            contentDescription = null,
                            tint = White
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        ChatDetailContent(modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun ChatDetailContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        OtherUserInfo(
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun OtherUserInfo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 85.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = Red,
                    shape = CircleShape
                ),
        )

        Text(
            text = "슈퍼 니카2",
            style = Title2,
            color = White,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ChatDetailScreenPreview() {
    SsamDTheme {
        ChatDetailScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0XFF0A0C10)
@Composable
private fun OtherUserInfoPreview() {
    SsamDTheme {
        OtherUserInfo()
    }
}
