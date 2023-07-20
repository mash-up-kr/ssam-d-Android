package com.mashup.data.util

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mashup.data.source.remote.source.paging.KeyLinkPagingSource
import com.mashup.domain.base.paging.PagedData

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/17
 */

fun <T : Any> createPager(
    pageSize: Int = 5,
    executor: suspend (Int, Int?) -> PagedData<List<T>>
): Pager<Int, T> = Pager(
    config = PagingConfig(
        pageSize = pageSize,
        enablePlaceholders = false
    ),
    pagingSourceFactory = { KeyLinkPagingSource(executor) }
)