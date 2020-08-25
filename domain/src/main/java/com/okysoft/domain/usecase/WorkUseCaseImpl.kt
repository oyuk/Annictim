package com.okysoft.domain.usecase

import com.okysoft.data.WatchKind
import com.okysoft.data.WorkRequestParams
import com.okysoft.domain.model.Work
import com.okysoft.domain.translator.WorkTranslator
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WorkUseCaseImpl (
    private val repository: com.okysoft.infra.repository.WorkRepository,
    private val translator: WorkTranslator
): WorkUseCase {

    override suspend fun get(requestParams: com.okysoft.data.WorkRequestParams): Flow<List<Work>> {
        return repository.get(requestParams)
            .map { response ->
                response.works.map { translator.translate(it) }
            }
    }

    override suspend fun me(requestParams: com.okysoft.data.WorkRequestParams): Flow<List<Work>> {
        return repository.me(requestParams)
            .map { response ->
                response.works.map { translator.translate(it) }
            }
    }

    override suspend fun request(requestParams: com.okysoft.data.WorkRequestParams): Flow<List<Work>> {
        return when (requestParams.type) {
            WorkRequestParams.Type.Me -> me(requestParams)
            WorkRequestParams.Type.Works -> get((requestParams))
        }
    }

    override suspend fun getWatchKind(workId: Int): Flow<WatchKind> {
        return repository.me(com.okysoft.data.WorkRequestParams(
            type = com.okysoft.data.WorkRequestParams.Type.Me,
            fields = com.okysoft.data.WorkRequestParams.Fields.Status,
            ids = listOf(workId),
            season = null,
            perPage = 1
        ))
            .map { response ->
                val watchKind = response.works.firstOrNull()?.watchKind ?: WatchKind.no_select
                return@map watchKind
            }
    }

}