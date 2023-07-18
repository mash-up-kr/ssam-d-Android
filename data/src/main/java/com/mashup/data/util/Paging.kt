package com.mashup.data.util

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mashup.data.source.remote.source.paging.KeyLinkPagingSource

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/17
 */

fun <T : Any> createPager(
    pageSize: Int = 10,
    initialLoadSize: Int = 10,
    executor: suspend (Int) -> List<T>
): Pager<Int, T> = Pager(
    config = PagingConfig(
        pageSize = pageSize,
        enablePlaceholders = false,
        initialLoadSize = initialLoadSize
    ),
    pagingSourceFactory = { KeyLinkPagingSource(executor) }
)