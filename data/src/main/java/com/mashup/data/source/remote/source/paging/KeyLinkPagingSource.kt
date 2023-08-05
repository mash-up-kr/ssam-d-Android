package com.mashup.data.source.remote.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mashup.domain.base.paging.PagedData
import kotlinx.coroutines.delay

/**
 * Ssam_D_Android
 * @author jaesung
 * @created 2023/07/16
 */
class KeyLinkPagingSource<T : Any> (
    private val executor: suspend (Int, Int?) -> PagedData<List<T>>
) : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: INITIAL_PAGE
        val loadSize = params.loadSize

        return try {
            val result = executor.invoke(page, loadSize)
            val isLastPage = (page == result.paging?.totalPage) || (result.paging?.totalPage == 0)

            if (result.data.isEmpty()) return LoadResult.Error(EmptyListException)

            LoadResult.Page(
                data = result.data,
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = if (isLastPage) null else page + 1
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    object EmptyListException: Exception()


    companion object {
        private const val INITIAL_PAGE = 1
    }
}