package com.mashup.presentation.ui.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/29
 */
@Composable
fun KeyLinkChatBottomSheet(
    modifier: Modifier = Modifier,
    onDisconnectSignal: () -> Unit = {},
    onReportUser: () -> Unit = {},
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        KeyLinkChatBottomSheetItem(
            actionTextId = R.string.bottom_sheet_disconnect,
            actionDrawableId = R.drawable.ic_close_24,
            textColor = White,
            tintColor = White,
            modifier = Modifier.fillMaxWidth(),
            onAction = { onDisconnectSignal() }
        )
        KeyLinkChatBottomSheetItem(
            actionTextId = R.string.bottom_sheet_declare,
            actionDrawableId = R.drawable.ic_declare_fill_24,
            textColor = Red,
            tintColor = Red,
            modifier = Modifier.fillMaxWidth(),
            onAction = { onReportUser() }
        )
    }
}

@Composable
fun KeyLinkChatBottomSheetItem(
    @StringRes actionTextId: Int,
    @DrawableRes actionDrawableId: Int,
    textColor: Color,
    tintColor: Color,
    modifier: Modifier = Modifier,
    onAction: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .clickable { onAction() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = actionDrawableId),
            tint = tintColor,
            contentDescription = ""
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(id = actionTextId),
            style = Body1,
            color = textColor,
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun KeyLinkKeywordBottomSheet(
    modifier: Modifier = Modifier,
    matchedKeywords: List<String> = listOf(),
) {
    Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
        Text(
            text = stringResource(R.string.bottom_sheet_matched_keywords),
            style = Title1,
            color = White,
            modifier = Modifier
        )

        FlowRow(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            matchedKeywords.forEach { keyword ->
                KeywordChip(
                    keyword = keyword,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun KeyLinkConnectedBottomSheet(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 20.dp),
    ) {
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            text = stringResource(R.string.bottom_sheet_connected_title),
            style = Title2,
            color = Gray10
        )
        Text(
            text = stringResource(R.string.bottom_sheet_connected_content),
            style = Body2,
            color = Gray10
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0XFFFFFFFF)
@Composable
fun KeyLinkChatBottomSheetPreview() {
    SsamDTheme {
        Surface(color = Gray02) {
            KeyLinkChatBottomSheet()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0XFFFFFFFF)
@Composable
fun KeyLinkKeywordBottomSheetPreview() {
    val matchedKeywords = listOf("매쉬업")
    SsamDTheme {
        Surface(color = Gray02) {
            KeyLinkKeywordBottomSheet(
                matchedKeywords = matchedKeywords
            )
        }
    }
}