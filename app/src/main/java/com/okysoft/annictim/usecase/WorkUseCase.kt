package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Work
import com.okysoft.annictim.infra.api.model.request.WorkRequestParams
import com.okysoft.annictim.presentation.WatchKind
import kotlinx.coroutines.Deferred

interface WorkUseCase {
    fun get(requestParams: WorkRequestParams): Deferred<List<Work>>
    fun me(requestParams: WorkRequestParams): Deferred<List<Work>>
    fun request(requestParams: WorkRequestParams): Deferred<List<Work>>
    fun getWatchKind(workId: Int): Deferred<WatchKind>
}
