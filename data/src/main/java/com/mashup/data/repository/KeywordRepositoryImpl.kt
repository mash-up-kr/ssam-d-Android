package com.mashup.data.repository

import com.mashup.data.source.remote.datasource.RemoteKeywordDataSource
import com.mashup.domain.repository.KeywordRepository
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/05
 */
class KeywordRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteKeywordDataSource
) : KeywordRepository {
    override suspend fun getRecommendKeywords(content: String): List<String> {
        return runCatching {
            remoteDataSource.getRecommendKeyword(content).keywords
        }.getOrThrow()
    }
}