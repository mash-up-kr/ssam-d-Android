package com.mashup.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
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
    keyword: String,
    modifier: Modifier = Modifier,
    style: TextStyle = Caption2
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
            text = keyword,
            style = style,
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
    val keywordText = buildAnnotatedString {
        append("#$text")
        appendInlineContent("inlineContent")
    }

    val inlineContent = mapOf(
        "inlineContent" to InlineTextContent(
            Placeholder(
                width = 24.sp,
                height = 24.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(
                painterResource(id = R.drawable.ic_delete_25),
                contentDescription = "키워드 지우기",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 4.dp)
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ) {
                        onKeywordDelete(index)
                    }
            )
        }
    )

    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .background(Gray02, shape = RoundedCornerShape(10.dp))
            .padding(vertical = 6.dp, horizontal = 20.dp)
    ) {
        Text(
            text = keywordText,
            color = White,
            fontSize = 24.sp,
            inlineContent = inlineContent
        )
    }
}

@Composable
fun KeywordBorderActionChip(
    text: String,
    index: Int,
    onKeywordDelete: (Int) -> Unit
) {
    val keywordText = buildAnnotatedString {
        append("#$text")
        appendInlineContent("inlineContent")
    }

    val inlineContent = mapOf(
        "inlineContent" to InlineTextContent(
            Placeholder(
                width = 14.sp,
                height = 14.sp,
                placeholderVerticalAlign = PlaceholderVerticalAlign.Center
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_close_8),
                contentDescription = "키워드 지우기",
                tint = Gray09,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp)
                    .clickable(
                        indication = null,
                        interactionSource = MutableInteractionSource()
                    ) {
                        onKeywordDelete(index)
                    }
            )
        }
    )

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
        Text(
            text = keywordText,
            color = White,
            fontSize = 14.sp,
            inlineContent = inlineContent
        )
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
