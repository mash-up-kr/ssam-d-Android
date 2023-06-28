package com.mashup.presentation.detail.chat.compose

import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */
@Composable
fun Chat(
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(count = 2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(5) {
            Message(modifier = Modifier)
        }
    }
}

@Composable
fun Message(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(156.dp)
            .aspectRatio(39f / 52f),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = Gray03,
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                modifier = Modifier.weight(8f),
                text = "메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지 메시지",
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                ),
                color = White
            )

            Text(
                modifier = Modifier.weight(1f),
                text = "나",
                style = Body2,
                color = White
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 4.dp),
                text = "날짜",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    lineHeight = 18.sp
                ),
                color = Gray06,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0XFF0A0C10)
@Composable
private fun ChatPreview() {
    SsamDTheme {
        Chat()
    }
}

@Preview(showBackground = true)
@Composable
private fun MessagePreview() {
    SsamDTheme {
        Message()
    }
}