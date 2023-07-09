package com.mashup.presentation.detail.chat.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.ui.theme.Red
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.Title2
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */

@Composable
fun OtherUserInfo(
    othersNickName: String,
    othersProfileImage: String = "",
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = Red,
                    shape = CircleShape
                )
        )

        Text(
            text = othersNickName,
            style = Title2,
            color = White
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0XFF0A0C10)
@Composable
private fun OtherUserInfoPreview() {
    SsamDTheme {
        OtherUserInfo(
            othersNickName = "아무개"
        )
    }
}