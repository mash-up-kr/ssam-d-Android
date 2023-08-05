package com.mashup.domain.usecase.crash

import com.mashup.domain.model.Crash
import com.mashup.domain.repository.CrashRepository
import com.mashup.domain.usecase.common.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCrashDetailUseCase @Inject constructor(
    private val crashRepository: CrashRepository
) : FlowUseCase<Long, Crash>() {

    override fun invoke(params: Long): Flow<Crash> =
        crashRepository.getCrashDetail(crashId = params)
}