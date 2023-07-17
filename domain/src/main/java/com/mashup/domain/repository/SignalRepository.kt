package com.mashup.domain.repository

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/13
 */
interface SignalRepository {

    suspend fun postSignal(content: String, keywords: List<String>)
}