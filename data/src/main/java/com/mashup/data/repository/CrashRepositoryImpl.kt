package com.mashup.data.repository

import androidx.paging.PagingData
import com.mashup.data.source.remote.source.datasource.RemoteCrashDataSource
import com.mashup.data.util.createPager
import com.mashup.data.util.suspendRunCatching
import com.mashup.domain.model.Crash
import com.mashup.domain.repository.CrashRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CrashRepositoryImpl @Inject constructor(
    private val remoteCrashDataSource: RemoteCrashDataSource
) : CrashRepository {
    override fun getCrashes(): Flow<PagingData<Crash>> {
        return createPager { page, loadSize ->
            remoteCrashDataSource.getCrashes(pageNumber = page, pageLength = loadSize)
                .toDomainModel()
        }.flow
    }

    override fun getCrashDetail(crashId: Long): Flow<Crash> = flow {
        val result = suspendRunCatching {
            remoteCrashDataSource.getCrashDetail(crashId).toDomainModel()
        }.getOrThrow()
        emit(result)
    }

    override suspend fun replyCrash(crashId: Long, content: String): Result<Unit> {
        return suspendRunCatching {
            remoteCrashDataSource.replyCrash(crashId, content)
        }
    }
}