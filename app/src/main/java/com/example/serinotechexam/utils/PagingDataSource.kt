package com.example.serinotechexam.utils

import android.util.Log
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.serinotechexam.model.Product
import kotlinx.coroutines.CoroutineScope

class PagingDataSource<T : Any>(
    private val config: Config<T>
): PagingSource<Int, T>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 0
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(config.skip) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(config.skip)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val position = params.key ?: STARTING_PAGE_INDEX

        val result = config.fetch.invoke(position ?: STARTING_PAGE_INDEX, params.loadSize)
        val list = config.list.invoke(result)
        val hasNextPage = config.hasNextPage.invoke(result)

        return LoadResult.Page(
            data = list,
            prevKey = if(position == STARTING_PAGE_INDEX) null else position.minus(config.skip),
            nextKey = if(!hasNextPage) null else position.plus(config.skip)
        )
    }
}

class Config<T>(
    val fetch: suspend (page: Int, pageSize: Int) -> Any,
    val list: (Any) -> List<T>,
    val skip: Int,
    val hasNextPage: (Any) -> Boolean
)