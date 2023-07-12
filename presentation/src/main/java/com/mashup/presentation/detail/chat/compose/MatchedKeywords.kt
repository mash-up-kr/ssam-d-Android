package com.mashup.presentation.detail.chat.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.ui.common.KeywordChip
import com.mashup.presentation.ui.theme.SsamDTheme

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/28
 */

@Composable
fun MatchedKeywords(
    matchedKeywords: List<String>,
    modifier: Modifier = Modifier,
    visible: Boolean,
) {

    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.Start
        ) {
            items(matchedKeywords) { keyword ->
                KeywordChip(keyword = keyword)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0XFF0A0C10)
@Composable
private fun MatchedKeywordsPreview() {
    val keywords = listOf("매쉬업", "일상", "디자인", "IT", "취준")
    SsamDTheme {
        MatchedKeywords(matchedKeywords = keywords, visible = true)
    }
}