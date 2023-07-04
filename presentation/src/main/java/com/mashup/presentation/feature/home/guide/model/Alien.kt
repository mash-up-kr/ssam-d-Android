package com.mashup.presentation.feature.home.guide.model

import androidx.annotation.DrawableRes
import com.mashup.presentation.R

enum class Alien(
    val alienName: String,
    @DrawableRes
    val alienImageSrc: Int,
    val description: String,
    val keyword: String
) {
    BLUE(
        alienName = "코아리안",
        alienImageSrc = R.drawable.img_profile_01,
        description = "자연과 식물 세계를 관리합니다.\n" +
                "평화와 조화를 추구하며, 종족 간의 교류와 협력을 강조합니다.",
        keyword = "루미스"
    ),
    ORANGE(
        alienName = "싸크라니스",
        alienImageSrc = R.drawable.img_profile_02,
        description = "우주와 천체에 관련된 지식을 다룹니다.\n" +
                "음악과 예술에 대한 이야기를 좋아합니다.",
        keyword = "심포니스"
    ),
    MINT(
        alienName = "키키우스",
        alienImageSrc = R.drawable.img_profile_03,
        description = "동물과 자연 세계를 사랑합니다.\n" +
                "행성 여행가로서 모험과 탐험을 즐깁니다.",
        keyword = "플러터"
    ),
    GREEN(
        alienName = "스콜라리스",
        alienImageSrc = R.drawable.img_profile_04,
        description = "비밀과 신비로움을 담당합니다.\n" +
                "지능과 사고력이 뛰어나며, 종족 간의 지식 공유와\n" +
                "학문적인 발전을 추구합니다.",
        keyword = "인텔"
    ),
    PINK(
        alienName = "마스케리안",
        alienImageSrc = R.drawable.img_profile_05,
        description = "창의적이고 열정적인 종족이며,\n" +
                "미래에 대한 영감을 가지고 혁신을 추구합니다.",
        keyword = "오로라"
    )
}