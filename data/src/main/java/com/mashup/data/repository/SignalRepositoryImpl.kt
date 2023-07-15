package com.mashup.data.repository

import com.mashup.data.source.remote.datasource.RemoteSignalDataSource
import com.mashup.domain.repository.SignalRepository
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/13
 */
class SignalRepositoryImpl @Inject constructor(
    private val remoteSignalDataSource: RemoteSignalDataSource
) : SignalRepository {

    override suspend fun postSignal(content: String, keywords: List<String>) {
        remoteSignalDataSource.postSignal(content, keywords)
    }
}