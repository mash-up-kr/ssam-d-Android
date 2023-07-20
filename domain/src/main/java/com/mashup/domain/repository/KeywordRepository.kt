package com.mashup.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/05
 */
interface KeywordRepository {

    fun getSubscribeKeywords(): Flow<List<String>>

    suspend fun postSubscribeKeywords(subscribeKeywords: List<String>): Result<Unit>

    suspend fun getRecommendKeywords(content: String): Flow<List<String>>

}