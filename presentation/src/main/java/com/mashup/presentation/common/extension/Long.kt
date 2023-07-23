package com.mashup.presentation.common.extension

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/17
 */
fun Long.getDisplayedTime(): String {
    val currentTimeMillis = System.currentTimeMillis()
    val diffMillis = currentTimeMillis - this

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

fun Long.getDisplayedDateAfterOneDay(): String {
    val currentTimeMillis = System.currentTimeMillis()
    val diffMillis = currentTimeMillis - this

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
        else -> {
            val formatter = SimpleDateFormat("M월 d일", Locale.KOREA)
            formatter.format(Date(this))
        }
    }
}

fun Long.getDisplayedDate(): String {
    val formatter = SimpleDateFormat("yyyy년 M월 d일 HH:mm", Locale.KOREA)
    return formatter.format(Date(this))
}

fun Long.getDisplayedDateWithDay(): String {
    val formatter = SimpleDateFormat("yyyy년 M월 d일 E요일 HH:mm", Locale.KOREA)
    return formatter.format(Date(this))
}
