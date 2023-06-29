package com.mashup.presentation.home.model

import java.util.concurrent.TimeUnit

/**
 * 임시 data model입니다.
 */
data class SignalUiModel(
    val profileImage: String? = null,
    val nickname: String,
    val summery: String,
    val keywords: List<String>,
    val uploadTime: Long
) {
    fun getDisplayedTime(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val diffMillis = currentTimeMillis - uploadTime

        return when {
            diffMillis < TimeUnit.MINUTES.toMillis(1) -> "지금"
            diffMillis < TimeUnit.HOURS.toMillis(1) -> {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(diffMillis)
                "${minutes}분 전"
            }
            diffMillis < TimeUnit.DAYS.toMillis(1) -> {
                val hours = TimeUnit.MILLISECONDS.toHours(diffMillis)
                "${hours}시간 전"
            }
            diffMillis < TimeUnit.DAYS.toMillis(30) -> {
                val days = TimeUnit.MILLISECONDS.toDays(diffMillis)
                "${days}일 전"
            }
            diffMillis < TimeUnit.DAYS.toMillis(365) -> {
                val months = (TimeUnit.MILLISECONDS.toDays(diffMillis) / 30).toInt()
                "${months}달 전"
            }
            else -> {
                "오래 전"
            }
        }
    }

    fun getKeywordSummeryList(): List<String> {
        return mutableListOf<String>().apply {
            if (keywords.size > MAX_KEYWORD_COUNT) {
                addAll(keywords.subList(0, MAX_KEYWORD_COUNT))
                add(
                    "+${keywords.size.minus(MAX_KEYWORD_COUNT)}"
                )
            } else {
                addAll(keywords)
            }
        }
    }

    companion object {
        private const val MAX_KEYWORD_COUNT = 3
    }
}
