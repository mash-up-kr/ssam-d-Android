package com.mashup.domain.usecase.common


abstract class CoroutineUseCase<I, O> {
    protected abstract suspend fun invoke(param: I): O

    suspend fun execute(param: I): O {
        return invoke(param)
    }
}