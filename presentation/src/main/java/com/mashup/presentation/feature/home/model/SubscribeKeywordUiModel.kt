package com.mashup.presentation.feature.home.model

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/17
 */
data class SubscribeKeywordUiModel(
    val subscribeKeywordsCount: Int,
    val subscribeKeywords: List<String>
) {
    companion object {
        fun List<String>.toUiModel() = SubscribeKeywordUiModel(
            subscribeKeywordsCount = this.size,
            subscribeKeywords = this
        )
    }
}