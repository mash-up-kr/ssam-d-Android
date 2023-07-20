package com.mashup.presentation.feature.detail.message.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mashup.presentation.ui.theme.Body1
import com.mashup.presentation.ui.theme.Gray10

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/03
 */
@Composable
fun MessageDetailContainer(
    othersName: String,
    date: String,
    message: String,
    matchedKeywords: List<String>,
    profileImage: String,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(modifier = modifier.verticalScroll(scrollState)) {
        MessageInfo(modifier = Modifier, othersName = othersName, date = date, profileImage = profileImage)

        MessageDetailContent(
            modifier = Modifier.padding(top = 16.dp),
            message = message
        )

        MatchedKeywordContainer(
            modifier = Modifier.padding(top = 52.dp),
            matchedKeywords = matchedKeywords
        )
    }
}

@Composable
fun MessageDetailContent(
    message: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = message,
        style = Body1,
        color = Gray10
    )
}