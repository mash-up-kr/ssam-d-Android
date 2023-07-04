package com.mashup.presentation.feature.detail.message.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/03
 */
@Composable
fun MessageInfo(
    othersName: String,
    date: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Red)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = othersName,
                style = Title1,
                color = White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = date,
                style = Caption1,
                color = Gray06
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MessageInfoPreview() {
    SsamDTheme {
        Surface(color = Black) {
            MessageInfo(othersName = "연날리기", date = "2023년 5월 30일")
        }
    }
}