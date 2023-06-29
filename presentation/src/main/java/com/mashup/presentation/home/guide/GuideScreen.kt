
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
