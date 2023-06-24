package com.mashup.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkRoundButton
import com.mashup.presentation.ui.theme.*

@Composable
fun HomeScreen() {
    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.img_space),
            contentDescription = stringResource(R.string.login_description_space),
            contentScale = ContentScale.Crop
        )
        Image(
            modifier = Modifier.align(Alignment.BottomCenter),
            painter = painterResource(R.drawable.img_blueplanet),
            contentDescription = stringResource(R.string.login_description_blueplanet)
        )
        EmptyContent()
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HomeScreenToolBar()
            HomeKeywordInfoContainer()
        }
    }
}

@Composable
private fun HomeScreenToolBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .background(color = Gray01)
            .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .width(8.7.dp)
                    .height(8.7.dp)
                    .background(color = Amber, shape = RoundedCornerShape(size = 8.7.dp))
            )
            Text(
                text = stringResource(id = R.string.home_my_planet, "Æ X-03"),
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = White
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_profile_fill),
            contentDescription = stringResource(id = R.string.home_my_page_icon_content_description),
            contentScale = ContentScale.None
        )
    }
}

@Composable
private fun HomeKeywordInfoContainer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Gray01)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .padding(start = 20.dp, top = 12.dp, end = 20.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_signal_32),
                contentDescription = stringResource(id = R.string.home_signal_icon_content_description),
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.Inside
            )
            Text(
                text = stringResource(id = R.string.home_subscribe_keywords, 4),
                fontSize = 14.sp,
                color = White
            )
        }
        Divider(
            color = Gray02,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun EmptyContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        EmptySignal()
    }
}

@Composable
private fun EmptySignal() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.home_planet_guide, "Æ X-03"),
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = White,
            textAlign = TextAlign.Center
        )
        KeyLinkRoundButton(text = stringResource(id = R.string.home_planet_guide_button)) {
            // TODO: navigate to 가이드
        }
    }
}

@Composable
private fun SignalCardKeywordsChips(keywords: List<String>) {
    val maxKeywordCount = 3

    val keywordChipItems = mutableListOf<String>().apply {
        if (keywords.size > maxKeywordCount) {
            addAll(keywords.subList(0, maxKeywordCount))
            add(
                "+${keywords.size.minus(maxKeywordCount)}"
            )
        } else {
            addAll(keywords)
        }
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.Start),
    ) {
        items(keywordChipItems.size) {
            SignalCardKeywordsChip(keyword = keywordChipItems[it])
        }
    }
}

@Composable
private fun SignalCardKeywordsChip(keyword: String) {
    Box(
        modifier = Modifier
            .background(color = Gray01, shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp),
            text = keyword,
            fontSize = 10.sp,
            color = Gray10,
            textAlign = TextAlign.Center,
            style = TextStyle(
                lineHeight = 2.5.em,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                )
            )
        )
    }
}
@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}