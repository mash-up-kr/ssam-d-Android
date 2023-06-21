package com.mashup.domain.usecase

abstract class BaseUseCase<I, O> {
    protected abstract suspend fun invoke(param: I): O

    suspend fun execute(param: I) {
        // TODO: 로그를 붙이거나, coroutineScope를 정하거나 등
        invoke(param)
    }
}