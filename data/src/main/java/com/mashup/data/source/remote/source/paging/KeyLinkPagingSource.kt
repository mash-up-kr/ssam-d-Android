package com.mashup.data.source.remote.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/16
 */
class KeyLinkPagingSource<T : Any>(
    private val executor: suspend (Int) -> List<T>
) : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: INITIAL_PAGE

        return try {
            val result = executor.invoke(page)

            LoadResult.Page(
                data = result,
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = if (result.isEmpty()) null else page + 1
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val INITIAL_PAGE = 1
    }
}