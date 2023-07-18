package com.mashup.data.repository

import androidx.paging.PagingData
import com.mashup.data.source.remote.source.datasource.RemoteSignalDataSource
import com.mashup.data.util.createPager
import com.mashup.domain.model.ReceivedSignal
import com.mashup.domain.repository.SignalRepository
import kotlinx.coroutines.flow.Flow
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

    override fun getReceivedSignal(): Flow<PagingData<ReceivedSignal>> {
        return createPager { page, loadSize ->
            remoteSignalDataSource.getReceivedSignal(pageNumber = page, pageLength = loadSize).toDomainModel()
        }.flow
    }
}