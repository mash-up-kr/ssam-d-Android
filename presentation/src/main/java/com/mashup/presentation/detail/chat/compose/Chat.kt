package com.mashup.presentation.detail.chat.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.detail.chat.MessageBackgroundColor
import com.mashup.presentation.ui.theme.Body2
import com.mashup.presentation.ui.theme.Gray06
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White

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
        items(30) {
            Message(modifier = Modifier)
        }
    }
}

@Composable
fun Message(
    modifier: Modifier = Modifier,
    backgroundColor: MessageBackgroundColor = MessageBackgroundColor.PURPLE
) {
    Card(
        modifier = modifier
            .width(156.dp)
            .aspectRatio(39f / 52f),
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp
    ) {
        Box(
            modifier = Modifier.background(
                brush = Brush.linearGradient(
                    colors = backgroundColor.getGradientColors(),
                    start = Offset(0f, Float.POSITIVE_INFINITY),
                    end = Offset(Float.POSITIVE_INFINITY, 0f)
                )
            ),
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
}

@Preview(showBackground = true, backgroundColor = 0XFF0A0C10)
@Composable
private fun ChatPreview() {
    SsamDTheme {
        Chat()
    }
}

@Preview(showBackground = true, name = "오랜지색")
@Composable
private fun MessagePreview1() {
    SsamDTheme {
        Message(
            backgroundColor = MessageBackgroundColor.ORANGE
        )
    }
}

@Preview(showBackground = true, name = "연두색")
@Composable
private fun MessagePreview2() {
    SsamDTheme {
        Message(
            backgroundColor = MessageBackgroundColor.GREEN
        )
    }
}

@Preview(showBackground = true, name = "민트색")
@Composable
private fun MessagePreview3() {
    SsamDTheme {
        Message(
            backgroundColor = MessageBackgroundColor.MINT
        )
    }
}

@Preview(showBackground = true, name = "핑크색")
@Composable
private fun MessagePreview4() {
    SsamDTheme {
        Message(
            backgroundColor = MessageBackgroundColor.PINK
        )
    }
}

@Preview(showBackground = true, name = "보라색")
@Composable
private fun MessagePreview5() {
    SsamDTheme {
        Message(
            backgroundColor = MessageBackgroundColor.PURPLE
        )
    }
}