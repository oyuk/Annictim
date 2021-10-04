package com.okysoft.infra.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.toInput
import com.apollographql.apollo.coroutines.await
import com.okysoft.common.PaginatableResponse
import com.okysoft.infra.WorkFeedsQuery
import com.okysoft.infra.fragment.WorkFeed
import com.okysoft.infra.repository.WorkFeedRepository

class WorkFeedRepositoryImpl(private val apoloClient: ApolloClient): WorkFeedRepository {

    override suspend fun fetch(season: String?, title: String?, after: String?): PaginatableResponse<WorkFeed> {
        val seasons: List<String> = season?.let {
            listOf(it)
        } ?: emptyList()
        val titles: List<String> = title?.let {
            listOf(it)
        } ?: emptyList()
        val response = apoloClient.query(WorkFeedsQuery(
            seasons.toInput(),
            titles.toInput(),
            Input.fromNullable(after))).await()
        val hasNextPage = response.data?.searchWorks?.pageInfo?.fragments?.pageInfo?.hasNextPage
        val nextCursor = response.data?.searchWorks?.pageInfo?.fragments?.pageInfo?.endCursor
        val list = response.data?.searchWorks?.nodes?.let { l ->
            l.mapNotNull { node -> node?.fragments?.workFeed }
        }
        if (hasNextPage != null && nextCursor != null && list != null) {
            return PaginatableResponse(hasNextPage, nextCursor, list)
        }
        throw NullPointerException()
    }

}