package com.mashup.data.util

import com.mashup.domain.exception.ConflictException
import com.mashup.domain.exception.KeyLinkException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.HttpException
import java.util.concurrent.CancellationException

suspend inline fun <T> suspendRunCatching(block: suspend () -> T): Result<T> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: HttpException) {
        when (e.code()) {
            409 -> Result.failure(ConflictException("ConflictException"))
            else -> {
                val rawData = e.response()?.errorBody()?.string() ?: ""
                val builder = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                val jsonAdapter: JsonAdapter<KeyLinkException> =
                    builder.adapter(KeyLinkException::class.java)
                val exception = jsonAdapter.fromJson(rawData)
                val message = exception?.message
                Result.failure(KeyLinkException(message))
            }
        }
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
