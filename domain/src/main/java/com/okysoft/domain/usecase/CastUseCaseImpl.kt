package com.okysoft.domain.usecase

import com.okysoft.data.CastRequestParams
import com.okysoft.domain.model.Cast
import com.okysoft.domain.translator.CastTranslator
import com.okysoft.infra.repository.CastRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CastUseCaseImpl (
    private val repository: CastRepository,
    private val translator: CastTranslator
): CastUseCase {

    override suspend fun get(requestParams: CastRequestParams): Flow<List<Cast>> {
        return repository.get(requestParams)
            .map { response ->
                response.casts.map { translator.translate(it) }
            }
    }
}
