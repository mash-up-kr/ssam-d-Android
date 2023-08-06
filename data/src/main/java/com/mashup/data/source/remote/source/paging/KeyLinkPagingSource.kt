package com.mashup.data.source.remote.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mashup.domain.base.paging.PagedData
import com.mashup.domain.exception.ConflictException
import com.mashup.domain.exception.EmptyListException
import com.mashup.domain.exception.KeyLinkException
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.delay
import retrofit2.HttpException

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
        } catch (e: HttpException) {
            val rawData = e.response()?.errorBody()?.string() ?: ""
            val builder = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val jsonAdapter: JsonAdapter<KeyLinkException> =
                builder.adapter(KeyLinkException::class.java)
            val exception = jsonAdapter.fromJson(rawData)
            val message = exception?.message
            LoadResult.Error(KeyLinkException(message))
        }
        catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val INITIAL_PAGE = 1
    }
}