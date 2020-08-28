package com.okysoft.infra.repository

import com.okysoft.common.PaginatableResponse
import com.okysoft.infra.fragment.WorkFeed
import kotlinx.coroutines.flow.Flow

interface WorkFeedRepository {
    fun fetch(season: String, after: String?): Flow<PaginatableResponse<WorkFeed>>
}