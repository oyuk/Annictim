package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Work
import com.okysoft.annictim.infra.api.model.request.WorkRequestParams
import com.okysoft.annictim.infra.api.repository.WorkRepository
import com.okysoft.annictim.presentation.WatchKind
import com.okysoft.annictim.translator.WorkTranslator
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.rx2.rxSingle


class WorkUseCaseImpl (
    private val repository: WorkRepository,
    private val translator: WorkTranslator
): WorkUseCase {

    override fun get(requestParams: WorkRequestParams): Single<List<Work>> {
        return GlobalScope.rxSingle {
            val response = repository.get(requestParams).await()
            val models = response.works.map { translator.translate(it) }
            return@rxSingle models
        }
    }

    override fun me(requestParams: WorkRequestParams): Single<List<Work>> {
        return GlobalScope.rxSingle {
            val response = repository.me(requestParams).await()
            val models = response.works.map { translator.translate(it) }
            return@rxSingle models
        }
    }

    override fun request(requestParams: WorkRequestParams): Single<List<Work>> {
        return when (requestParams.type) {
            WorkRequestParams.Type.Me -> me(requestParams)
            WorkRequestParams.Type.Works -> get((requestParams))
        }
    }

    override fun getWatchKind(workId: Int): Single<WatchKind> {
        return GlobalScope.rxSingle {
            val response = repository.me(WorkRequestParams(
                type = WorkRequestParams.Type.Me,
                fields = WorkRequestParams.Fields.Status,
                ids = listOf(workId),
                season = null,
                perPage = 1
            )).await()
            val watchKind = response.works.firstOrNull()?.watchKind ?: WatchKind.no_select
            return@rxSingle watchKind
        }
    }

}