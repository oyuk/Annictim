package com.okysoft.domain.usecase

import com.okysoft.data.WatchKind
import com.okysoft.domain.model.Work
import kotlinx.coroutines.Deferred

interface WorkUseCase {
    fun get(requestParams: com.okysoft.data.WorkRequestParams): Deferred<List<Work>>
    fun me(requestParams: com.okysoft.data.WorkRequestParams): Deferred<List<Work>>
    fun request(requestParams: com.okysoft.data.WorkRequestParams): Deferred<List<Work>>
    fun getWatchKind(workId: Int): Deferred<WatchKind>
}
