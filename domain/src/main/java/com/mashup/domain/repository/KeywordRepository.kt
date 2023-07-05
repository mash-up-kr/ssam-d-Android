package com.mashup.domain.repository

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/05
 */
interface KeywordRepository {

    suspend fun getRecommendKeywords(content: String) : List<String>

}