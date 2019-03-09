package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Work
import com.okysoft.annictim.infra.api.model.request.WorkRequestParams
import com.okysoft.annictim.presentation.WatchKind
import io.reactivex.Single

interface WorkUseCase {
    fun get(requestParams: WorkRequestParams): Single<List<Work>>
    fun me(requestParams: WorkRequestParams): Single<List<Work>>
    fun request(requestParams: WorkRequestParams): Single<List<Work>>
    fun getWatchKind(workId: Int): Single<WatchKind>
}
