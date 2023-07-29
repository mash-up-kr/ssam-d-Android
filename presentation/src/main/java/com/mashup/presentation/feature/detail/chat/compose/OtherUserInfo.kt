package com.mashup.presentation.feature.detail.chat.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.Title2
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OtherUserInfo(
    modifier: Modifier = Modifier,
    othersNickName: String,
    othersProfileImage: String = ""
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GlideImage(
            modifier = Modifier.size(36.dp),
            model = othersProfileImage,
            contentDescription = "",
            contentScale = ContentScale.Crop
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