package com.mashup.domain.usecase

import com.mashup.domain.repository.KeywordRepository
import com.mashup.domain.usecase.common.CoroutineUseCase
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/21
 */
class UpdateSubscribeKeywordsUseCase @Inject constructor(
    private val keywordRepository: KeywordRepository
) : CoroutineUseCase<List<String>, Result<Unit>>() {
    override suspend fun invoke(param: List<String>): Result<Unit> {
        return keywordRepository.postSubscribeKeywords(subscribeKeywords = param)
    }
}