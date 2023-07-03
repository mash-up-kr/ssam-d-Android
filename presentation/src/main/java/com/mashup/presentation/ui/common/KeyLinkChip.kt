package com.mashup.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.R
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/22
 */

@Composable
fun KeywordChip(
    modifier: Modifier = Modifier,
    keyword: String
) {
    Box(
        modifier = modifier
            .background(
                color = Gray01,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = "#$keyword",
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 18.sp
            ),
            color = Gray10,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun KeywordActionChip(
    text: String,
    index: Int,
    onKeywordDelete: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .background(Gray02, shape = RoundedCornerShape(10.dp))
            .padding(vertical = 6.dp, horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "#$text",
                color = White,
                fontSize = 24.sp
            )

            Icon(
                painterResource(id = R.drawable.ic_delete_25),
                contentDescription = "키워드 지우기",
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ) {
                        onKeywordDelete(index)
                    }
            )
        }
    }
}

@Composable
fun KeywordBorderActionChip(
    text: String,
    index: Int,
    onKeywordDelete: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .background(Gray02, shape = RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(Purple, Mint)
                ),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "#$text",
                color = White,
                fontSize = 14.sp
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_close_8),
                contentDescription = "키워드 지우기",
                tint = Gray09,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ) {
                        onKeywordDelete(index)
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChipPreview() {
    SsamDTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            KeywordChip(
                keyword = "매쉬업"
            )
            KeywordActionChip(
                text = "키워드",
                index = 0,
                onKeywordDelete = {}
            )
            KeywordBorderActionChip(
                text = "하이볼",
                index = 0,
                onKeywordDelete = {}
            )
        }
    }
}

