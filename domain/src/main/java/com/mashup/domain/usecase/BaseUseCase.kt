package com.mashup.domain.usecase


abstract class BaseUseCase<I, O> {
    protected abstract suspend fun invoke(param: I): O

    suspend fun execute(param: Long): O {
        return invoke(param)
    }
}