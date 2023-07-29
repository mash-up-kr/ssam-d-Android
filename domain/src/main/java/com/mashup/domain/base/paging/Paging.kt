package com.mashup.domain.base.paging

import com.mashup.domain.base.DomainModel

data class Paging(
    val loadedSize: Int,
    val totalPage: Int,
) : DomainModel