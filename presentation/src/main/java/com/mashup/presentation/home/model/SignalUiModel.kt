package com.mashup.presentation.home.model

/**
 * 임시 data model입니다.
 */
data class SignalUiModel(
    val profileImage: String? = null,
    val nickname: String,
    val summery: String,
    val keywords: List<String>,
    val uploadTime: Long
)
