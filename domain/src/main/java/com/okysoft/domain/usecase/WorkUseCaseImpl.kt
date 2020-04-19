package com.okysoft.domain.usecase

import com.okysoft.data.WatchKind
import com.okysoft.data.WorkRequestParams
import com.okysoft.domain.model.Work
import com.okysoft.domain.translator.WorkTranslator
import kotlinx.coroutines.*

class WorkUseCaseImpl (
    private val repository: com.okysoft.infra.repository.WorkRepository,
    private val translator: WorkTranslator
): WorkUseCase {

    override suspend fun get(requestParams: com.okysoft.data.WorkRequestParams): List<Work> {
        return withContext(Dispatchers.IO) {
            val response = repository.get(requestParams)
            val models = response.works.map { translator.translate(it) }
            return@withContext models
        }
    }

    override suspend fun me(requestParams: com.okysoft.data.WorkRequestParams): List<Work> {
        return withContext(Dispatchers.IO) {
            val response = repository.me(requestParams)
            val models = response.works.map { translator.translate(it) }
            return@withContext models
        }
    }

    override suspend fun request(requestParams: com.okysoft.data.WorkRequestParams): List<Work> {
        return when (requestParams.type) {
            WorkRequestParams.Type.Me -> me(requestParams)
            WorkRequestParams.Type.Works -> get((requestParams))
        }
    }

    override suspend fun getWatchKind(workId: Int): WatchKind {
        return withContext(Dispatchers.IO) {
            val response = repository.me(com.okysoft.data.WorkRequestParams(
                type = com.okysoft.data.WorkRequestParams.Type.Me,
                fields = com.okysoft.data.WorkRequestParams.Fields.Status,
                ids = listOf(workId),
                season = null,
                perPage = 1
            ))
            val watchKind = response.works.firstOrNull()?.watchKind ?: WatchKind.no_select
            return@withContext watchKind
        }
    }

}