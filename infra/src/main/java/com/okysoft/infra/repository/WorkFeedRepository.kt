package com.okysoft.infra.repository

import com.okysoft.common.PaginatableResponse
import com.okysoft.infra.fragment.WorkFeed

interface WorkFeedRepository {
    suspend fun fetch(season: String?, title: String?, after: String?): PaginatableResponse<WorkFeed>
}