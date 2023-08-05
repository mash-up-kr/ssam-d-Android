package com.mashup.domain.usecase.crash

import androidx.paging.PagingData
import com.mashup.domain.model.Crash
import com.mashup.domain.repository.CrashRepository
import com.mashup.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCrashesUseCase @Inject constructor(
    private val crashRepository: CrashRepository
) : FlowUseCase<Unit, PagingData<Crash>>() {
    override fun invoke(params: Unit): Flow<PagingData<Crash>> = crashRepository.getCrashes()
}