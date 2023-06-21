package com.mashup.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.R
import com.mashup.presentation.common.extension.drawBorder
import com.mashup.presentation.ui.theme.Gray02
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/22
 */
@Composable
fun KeywordChip(
    text: String,
    drawBorder: Boolean,
    index: Int,
    chipTextSize: TextUnit,
    onKeywordDelete: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .background(Gray02, shape = RoundedCornerShape(10.dp))
            .drawBorder(drawBorder)
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
                fontSize = chipTextSize
            )

            Icon(
                painterResource(id = R.drawable.ic_delete),
                contentDescription = "키워드 지우기",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
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
                text = "키워드",
                drawBorder = false,
                chipTextSize = 24.sp,
                index = 0,
                onKeywordDelete = {}
            )
            KeywordChip(
                text = "버스커버스커장범준노래",
                drawBorder = true,
                chipTextSize = 14.sp,
                index = 0,
                onKeywordDelete = {}
            )
        }
    }
}

