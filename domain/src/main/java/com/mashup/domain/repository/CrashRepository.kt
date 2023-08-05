package com.mashup.domain.repository

import androidx.paging.PagingData
import com.mashup.domain.model.Crash
import kotlinx.coroutines.flow.Flow

interface CrashRepository {
    fun getCrashes(): Flow<PagingData<Crash>>

    fun getCrashDetail(crashId: Long): Flow<Crash>

    suspend fun replyCrash(crashId: Long, content: String): Result<Unit>
}