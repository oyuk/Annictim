package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Cast
import com.okysoft.annictim.infra.api.model.request.CastRequestParams
import com.okysoft.annictim.infra.api.repository.CastRepository
import com.okysoft.annictim.translator.CastTranslator
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class CastUseCaseImpl (
    private val repository: CastRepository,
    private val translator: CastTranslator
): CastUseCase {

    override fun get(requestParams: CastRequestParams): Deferred<List<Cast>> {
        return GlobalScope.async {
            val response = repository.get(requestParams).await()
            val models = response.casts.map { translator.translate(it) }
            return@async models
        }
    }

}