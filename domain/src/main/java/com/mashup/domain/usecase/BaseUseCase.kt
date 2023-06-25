package com.mashup.domain.usecase

import timber.log.Timber

abstract class BaseUseCase<I, O> {
    protected abstract suspend fun invoke(param: I): O

    suspend fun execute(param: I): O {
        Timber.i("param : $param")
        return invoke(param).also {
            Timber.i("result : $it")
        }
    }
}