package com.mashup.domain.usecase

import com.mashup.domain.repository.KeywordRepository
import com.mashup.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/19
 */
class GetSubscribeKeywordsUseCase @Inject constructor(
    private val keywordRepository: KeywordRepository
) : FlowUseCase<Unit, List<String>>() {
    override fun invoke(params: Unit): Flow<List<String>> = keywordRepository.getSubscribeKeywords()
}