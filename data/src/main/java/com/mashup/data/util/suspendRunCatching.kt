package com.mashup.data.util

import com.mashup.domain.exception.ConflictException
import retrofit2.HttpException
import java.util.concurrent.CancellationException

suspend fun <T> suspendRunCatching(block: suspend () -> T): Result<T> = try {
    Result.success(block())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (httpException: HttpException) {
    when (httpException.code()) {
        409 -> throw ConflictException("ConflictException")
        else -> Result.failure(httpException)
    }
} catch (exception: Exception) {
    Result.failure(exception)
}
