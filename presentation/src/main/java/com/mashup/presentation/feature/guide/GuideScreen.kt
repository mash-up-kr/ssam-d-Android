package com.mashup.presentation.feature.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.feature.guide.model.Alien
import com.mashup.presentation.ui.common.KeyLinkButton
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.*

@Composable
fun GuideRoute(
    onBackClick: () -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    GuideScreen(
        modifier = modifier,
        onBackClick = onBackClick,
        onButtonClick = onButtonClick
    )
}

@Composable
fun GuideScreen(
    onBackClick: () -> Unit,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img_home_guide),
            contentDescription = stringResource(id = R.string.home_guide_background_content_description),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            KeyLinkToolbar(
                backgroundColor = Color.Transparent,
                onClickBack = onBackClick
            )
            Column(
                modifier = modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PlanetGuide()
                Spacer(modifier = Modifier.height(72.dp))
                Aliens()
                Spacer(modifier = Modifier.height(48.dp))
                PlanetGuideFooter(
                    onButtonClick = onButtonClick
                )
            }
        }

    }
}

@Composable
fun PlanetGuide() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.home_guide_planet),
            style = Heading1,
            color = White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.home_guide_new_feature_coming_soon),

            modifier = Modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    val brush = Brush.horizontalGradient(listOf(Purple, Mint))
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush, blendMode = BlendMode.SrcAtop)
                    }
                },
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(id = R.string.home_guide_current_planet_description),
            style = Body1,
            color = Gray07,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Aliens() {
    val aliens = Alien.values().toList()
    Column(
        verticalArrangement = Arrangement.spacedBy(60.dp)
    ) {
        for (alien in aliens) {
            AlienInfo(alien)
        }
    }
}

@Composable
fun AlienInfo(alien: Alien) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = alien.alienImageSrc),
            contentDescription = stringResource(id = R.string.home_guide_alien_content_description)
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = alien.alienName,
            style = Body1,
            color = Gray10
        )
        Spacer(modifier = Modifier.height(16.dp))
        AlienCard(alien)
    }
}

@Composable
fun AlienCard(alien: Alien) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = BlackAlpha60,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = alien.description,
                style = Body2,
                color = White,
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.home_guide_alien_card_keyword, alien.keyword),
                style = Caption2,
                color = Gray10,
                modifier = Modifier
                    .background(
                        color = Black, shape = RoundedCornerShape(10.dp)
                    )
                    .padding(vertical = 4.dp, horizontal = 8.dp),
            )
        }
    }
}

@Composable
fun PlanetGuideFooter(
    onButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(81.dp)
    ) {
        Text(text = stringResource(id = R.string.home_guide_footer_description),
            style = Title1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    val brush = Brush.horizontalGradient(listOf(Purple, Mint))
                    onDrawWithContent {
                        drawContent()
                        drawRect(brush, blendMode = BlendMode.SrcAtop)
                    }
                })
        KeyLinkButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onButtonClick,
            text = stringResource(id = R.string.button_send_signal)
        )
    }
}

@Preview
@Composable
fun GuideScreenPreview() {
    GuideScreen(onBackClick = {}, onButtonClick = {})
}
