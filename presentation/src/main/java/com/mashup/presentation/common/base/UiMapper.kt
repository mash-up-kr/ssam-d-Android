package com.mashup.presentation.common.base

import com.mashup.domain.base.DomainModel

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/17
 */
interface UiMapper<T : DomainModel, O : UiModel?> {
    fun toUiModel(domain: T): O
}
