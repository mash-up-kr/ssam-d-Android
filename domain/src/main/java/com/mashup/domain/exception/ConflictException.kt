package com.mashup.domain.exception

data class ConflictException(override val message: String?) : Exception(message)