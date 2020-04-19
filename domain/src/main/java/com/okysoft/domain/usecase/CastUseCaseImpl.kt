package com.okysoft.domain.usecase

import com.okysoft.data.CastRequestParams
import com.okysoft.domain.model.Cast
import com.okysoft.domain.translator.CastTranslator
import com.okysoft.infra.repository.CastRepository
import kotlinx.coroutines.*

class CastUseCaseImpl (
    private val repository: CastRepository,
    private val translator: CastTranslator
): CastUseCase {

    override suspend fun get(requestParams: CastRequestParams, dispatcher: CoroutineDispatcher): List<Cast> {
        return withContext(dispatcher) {
            val response = repository.get(requestParams)
            val models = response.casts.map { translator.translate(it) }
            return@withContext models
        }
    }

}