package com.okysoft.infra.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.toFlow
import com.okysoft.common.PaginatableResponse
import com.okysoft.infra.WorkFeedsQuery
import com.okysoft.infra.fragment.WorkFeed
import com.okysoft.infra.repository.WorkFeedRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WorkFeedRepositoryImpl(private val apoloClient: ApolloClient): WorkFeedRepository {

    @ExperimentalCoroutinesApi
    override fun fetch(season: String, after: String?): Flow<PaginatableResponse<WorkFeed>> {
        return apoloClient.query(WorkFeedsQuery(season, Input.fromNullable(after))).toFlow()
            .map {
                val hasNextPage = it.data?.searchWorks?.pageInfo?.fragments?.pageInfo?.hasNextPage
                val nextCursor = it.data?.searchWorks?.pageInfo?.fragments?.pageInfo?.endCursor
                val list = it.data?.searchWorks?.nodes?.let { l ->
                    l.mapNotNull { node -> node?.fragments?.workFeed }
                }
                if (hasNextPage != null && nextCursor != null && list != null) {
                    return@map PaginatableResponse(hasNextPage, nextCursor, list)
                }
                throw NullPointerException()
            }
    }

}