package com.mashup.domain.base

interface DomainMapper<T : DomainModel?> {

    fun toDomainModel(): T
}