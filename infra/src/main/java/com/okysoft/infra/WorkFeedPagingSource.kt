package com.okysoft.infra

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.okysoft.data.WorkRequestParams
import com.okysoft.infra.fragment.WorkFeed
import com.okysoft.infra.repository.WorkFeedRepository

class WorkFeedPagingSource(
    private val backend: WorkFeedRepository,
    private val workRequestParams: WorkRequestParams): PagingSource<String, WorkFeed>()
{
    override fun getRefreshKey(state: PagingState<String, WorkFeed>): String? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, WorkFeed> {
        return try {
            val nextKey = params.key
            val response = backend.fetch(workRequestParams.season, workRequestParams.title, nextKey)
            LoadResult.Page(
                data = response.items,
                prevKey = null,
                nextKey = response.nextCursor)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}