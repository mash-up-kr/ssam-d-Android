package com.mashup.domain.base.paging

import com.mashup.domain.base.DomainModel

data class PagedData<T>(
    val data: T,
    val paging: Paging?
) : DomainModel