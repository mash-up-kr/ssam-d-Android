package com.mashup.data.repository

import androidx.paging.PagingData
import com.mashup.data.source.remote.source.datasource.RemoteSignalDataSource
import com.mashup.data.util.createPager
import com.mashup.data.util.suspendRunCatching
import com.mashup.domain.model.Signal
import com.mashup.domain.repository.SignalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/13
 */
class SignalRepositoryImpl @Inject constructor(
    private val remoteSignalDataSource: RemoteSignalDataSource
) : SignalRepository {

    override suspend fun sendSignal(content: String, keywords: List<String>) {
        remoteSignalDataSource.postSignal(content, keywords)
    }

    override fun getReceivedSignal(): Flow<PagingData<Signal>> {
        return createPager { page, loadSize ->
            remoteSignalDataSource.getReceivedSignal(
                pageNumber = page,
                pageLength = loadSize
            ).toDomainModel()
        }.flow
    }

    override fun getReceivedSignalDetail(signalId: Long): Flow<Signal> = flow {
        val result = suspendRunCatching {
            remoteSignalDataSource.getReceivedSignalDetail(signalId).toDomainModel()
        }.getOrThrow()
        emit(result)
    }

    override suspend fun sendReceivedSignalReply(signalId: Long, content: String) {
        suspendRunCatching {
            remoteSignalDataSource.postReceivedSignalReply(signalId = signalId, content = content)
        }
    }
}