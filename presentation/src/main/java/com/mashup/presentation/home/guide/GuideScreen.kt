
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
