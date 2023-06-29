package com.mashup.presentation.home.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.presentation.R
import com.mashup.presentation.ui.common.KeyLinkButton
import com.mashup.presentation.ui.common.KeyLinkToolbar
import com.mashup.presentation.ui.theme.*

@Composable
fun PlanetGuideScreen(
    modifier: Modifier = Modifier
) {
    Scaffold {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img_home_guide),
            contentDescription = "홈 가이드 백그라운드",
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            KeyLinkToolbar(
                backgroundColor = Color.Transparent
            ) {
                // TODO: 뒤로가기
            }
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
                PlanetGuideFooter()
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
            text = "행성 Zylix-6",
            style = Heading1,
            color = White
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "곧 다른 행성 이동 가능한 기능이 출시 예정입니다. \n거기는 어떤 종족들이 살고 있을까요?",

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
            text = "Zylix-6의 종족들은 상호 간의 연결성과 유대감을 중요시합니다. 그들은 공동체 의식을 갖고 있으며, 협력과 상호작용을 통해 문화적인 발전과 지식의 공유를 추구합니다. 이를 통해 행성 전체의 번영을 이루는데 기여합니다.",
            style = Body1,
            color = Gray07,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Aliens() {
    Column {
        AlienInfo()
    }
}

@Composable
fun AlienInfo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_avatar), contentDescription = "외계 생명체"
        )
        Text(
            text = "코아리안", style = Body1, color = Gray10
        )
        Spacer(modifier = Modifier.height(16.dp))
        AlienCard()
    }
}

@Composable
fun AlienCard() {
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
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "자연과 식물 세계를 관리합니다. \n평화와 조화를 추구하며, 종족 간의 교류와 협력을 강조합니다.",
                style = Body2,
                color = White,
                textAlign = TextAlign.Center
            )

            Text(
                text = "키워드: 루미스",
                style = Caption,
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
fun PlanetGuideFooter() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(81.dp)
    ) {
        Text(text = "“원하는 종족과 얘기하려면 시그널에 키워드를 추가해서 보내보세요. 같은 종족이라면 통하는 시그널이 있을 거예요.”",
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
        KeyLinkButton(modifier = Modifier.fillMaxWidth(), text = "시그널 보내기")
    }
}

@Preview
@Composable
fun PreviewGuideScreen() {
    PlanetGuideScreen()
}
