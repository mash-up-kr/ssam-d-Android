package com.mashup.presentation.feature.detail.message.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeywordChip
import com.mashup.presentation.ui.theme.Black
import com.mashup.presentation.ui.theme.Mint
import com.mashup.presentation.ui.theme.SsamDTheme
import com.mashup.presentation.ui.theme.Title2

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/03
 */

@Composable
fun MatchedKeywordContainer(
    matchedKeywords: List<String>,
    modifier: Modifier = Modifier
) {
    MatchedKeywordHeader(
        modifier = modifier,
        matchedKeywords = matchedKeywords.size
    )
    MatchedKeywordsContent(matchedKeywords = matchedKeywords)
}

@Composable
fun MatchedKeywordHeader(
    matchedKeywords: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_frequency_mint_16),
            tint = Mint,
            contentDescription = stringResource(id = R.string.content_description_chat_item_frequency)
        )

        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = stringResource(
                id = R.string.message_detail_matched_signal_count,
                matchedKeywords
            ),
            style = Title2,
            color = Mint
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MatchedKeywordsContent(
    matchedKeywords: List<String>,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = Modifier.padding(top = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        matchedKeywords.forEach { keyword ->
            KeywordChip(
                modifier = Modifier.padding(top = 8.dp),
                keyword = keyword
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun MatchedKeywordContentPreview() {
    val matchedKeywords = listOf("매쉬업", "일상", "디자인", "IT", "취준", "일상", "디자인", "IT", "취준")
    SsamDTheme {
        Surface(color = Black) {
            MatchedKeywordContainer(matchedKeywords)
        }
    }
}