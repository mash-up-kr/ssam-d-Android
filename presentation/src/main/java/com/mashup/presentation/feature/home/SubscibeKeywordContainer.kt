package com.mashup.presentation.feature.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.theme.Body2
import com.mashup.presentation.ui.theme.White

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/18
 */
@Composable
fun HomeKeywordInfoContainer(
    subscribeKeywordsCount: Int,
    subscribeKeywords: List<String>,
    visible: Boolean,
    topBarBackgroundColor: Color,
    onKeywordContainerClick: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(),
        exit = shrinkVertically()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = topBarBackgroundColor)
                .clickable { onKeywordContainerClick() }
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
                    painter = painterResource(id = R.drawable.ic_tag_mint),
                    contentDescription = stringResource(id = R.string.home_signal_icon_content_description),
                    modifier = Modifier.size(24.dp),
                    contentScale = ContentScale.Inside,
                )
                Text(
                    text = stringResource(id = R.string.home_subscribe_keywords, subscribeKeywordsCount),
                    style = Body2,
                    color = White
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_chevron_right_24),
                    contentDescription = stringResource(id = R.string.home_signal_icon_content_description),
                    modifier = Modifier.size(16.dp),
                    contentScale = ContentScale.Inside,
                )
            }
        }
    }
}
