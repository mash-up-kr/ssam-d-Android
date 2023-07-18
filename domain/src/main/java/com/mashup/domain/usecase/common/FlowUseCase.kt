package com.mashup.domain.usecase.common

import kotlinx.coroutines.flow.Flow

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/17
 */
abstract class FlowUseCase<I, O> {
    protected abstract fun invoke(params: I): Flow<O>

    fun execute(params: I): Flow<O> = invoke(params)
}