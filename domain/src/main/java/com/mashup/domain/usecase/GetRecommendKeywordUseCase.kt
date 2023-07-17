package com.mashup.domain.usecase

import com.mashup.domain.repository.KeywordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/05
 */
class GetRecommendKeywordUseCase @Inject constructor(
    private val keywordRepository: KeywordRepository
) : BaseUseCase<String, Flow<List<String>>>(){

    override suspend fun invoke(param: String): Flow<List<String>> {
        return keywordRepository.getRecommendKeywords(content = param)
    }
}