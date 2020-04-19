package com.okysoft.domain.usecase

import com.okysoft.data.WatchKind
import com.okysoft.domain.model.Work
import kotlinx.coroutines.Deferred

interface WorkUseCase {
    suspend fun get(requestParams: com.okysoft.data.WorkRequestParams): List<Work>
    suspend fun me(requestParams: com.okysoft.data.WorkRequestParams): List<Work>
    suspend fun request(requestParams: com.okysoft.data.WorkRequestParams): List<Work>
    suspend fun getWatchKind(workId: Int): WatchKind
}
