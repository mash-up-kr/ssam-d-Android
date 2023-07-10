package com.mashup.presentation.feature.subscribe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.*
import com.mashup.presentation.ui.theme.*

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/06/25
 */
@Composable
fun SubscribeRoute(
    onBackClick: () -> Unit,
    onSaveButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SubscribeViewModel = hiltViewModel()
) {

    SubscribeKeywordScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onSaveButtonClick = onSaveButtonClick
    )
}
@Composable
fun SubscribeKeywordScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSaveButtonClick: () -> Unit = {}
) {
    val keywords = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.fillMaxSize()) {
        KeyLinkToolbar(
            onClickBack = onBackClick
        )
        SubscribeKeywordContent(
            modifier = Modifier.weight(1f),
            keywords = keywords,
            onKeywordAdd = {
                keywords.add(it)
            },
            onKeywordDelete = {
                keywords.removeAt(it)
            }
        )
        KeyLinkButton(
            text = stringResource(R.string.button_save),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp, start = 20.dp, end = 20.dp),
            enable = !keywords.isEmpty(),
            onClick = onSaveButtonClick
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SubscribeKeywordContent(
    keywords: List<String>,
    modifier: Modifier = Modifier,
    onKeywordAdd: (String) -> Unit,
    onKeywordDelete: (Int) -> Unit,
) {
    var keyword by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    LaunchedEffect(keywords.size) {
        scrollState.animateScrollTo(Int.MAX_VALUE)
    }

    Column(modifier = modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp)) {
        Text(
            text = stringResource(R.string.subscribe_keyword_title),
            style = Heading3,
            color = White
        )

        Text(
            text = stringResource(R.string.subscribe_keyword_subtitle),
            style = Body1,
            color = Gray06,
            modifier = Modifier.padding(top = 8.dp)
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(top = 28.dp)
                .verticalScroll(scrollState)
        ) {
            keywords.forEachIndexed { i, keyword ->
                KeywordBorderActionChip(
                    text = keyword,
                    index = i,
                    onKeywordDelete = onKeywordDelete
                )
            }

            KeyLinkOnBoardingTextField(
                value = keyword,
                onValueChange = { keyword = it },
                hint = "예) 매쉬업",
                fontSize = 14.sp,
                onClickDone = {
                    onKeywordAdd(keyword)
                    keyword = ""
                },
                minLength = 1
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SubscribeKeywordScreenPreview() {
    SsamDTheme {
        SubscribeKeywordScreen()
    }
}
