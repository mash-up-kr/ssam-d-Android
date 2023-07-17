package com.mashup.presentation.feature.home.model

import androidx.paging.PagingData

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/17
 */
data class HomeUiModel(
    val subscribeKeywords: SubscribeKeywordUiModel,
    val receivedSignals: PagingData<SignalUiModel>
)
