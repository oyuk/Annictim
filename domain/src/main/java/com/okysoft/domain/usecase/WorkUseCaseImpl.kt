package com.okysoft.domain.usecase

import com.okysoft.data.WatchKind
import com.okysoft.domain.model.Work
import com.okysoft.domain.translator.WorkTranslator
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class WorkUseCaseImpl (
    private val repository: com.okysoft.infra.repository.WorkRepository,
    private val translator: WorkTranslator
): WorkUseCase {

    override fun get(requestParams: com.okysoft.data.WorkRequestParams): Deferred<List<Work>> {
        return GlobalScope.async {
            val response = repository.get(requestParams).await()
            val models = response.works.map { translator.translate(it) }
            return@async models
        }
    }

    override fun me(requestParams: com.okysoft.data.WorkRequestParams): Deferred<List<Work>> {
        return GlobalScope.async {
            val response = repository.me(requestParams).await()
            val models = response.works.map { translator.translate(it) }
            return@async models
        }
    }

    override fun request(requestParams: com.okysoft.data.WorkRequestParams): Deferred<List<Work>> {
        return when (requestParams.type) {
            com.okysoft.data.WorkRequestParams.Type.Me -> me(requestParams)
            com.okysoft.data.WorkRequestParams.Type.Works -> get((requestParams))
        }
    }

    override fun getWatchKind(workId: Int): Deferred<WatchKind> {
        return GlobalScope.async {
            val response = repository.me(com.okysoft.data.WorkRequestParams(
                type = com.okysoft.data.WorkRequestParams.Type.Me,
                fields = com.okysoft.data.WorkRequestParams.Fields.Status,
                ids = listOf(workId),
                season = null,
                perPage = 1
            )).await()
            val watchKind = response.works.firstOrNull()?.watchKind ?: WatchKind.no_select
            return@async watchKind
        }
    }

}