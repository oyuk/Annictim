package com.okysoft.domain.usecase

import com.okysoft.domain.model.Cast
import com.okysoft.domain.translator.CastTranslator
import com.okysoft.infra.repository.CastRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class CastUseCaseImpl (
    private val repository: CastRepository,
    private val translator: CastTranslator
): CastUseCase {

    override fun get(requestParams: com.okysoft.data.CastRequestParams): Deferred<List<Cast>> {
        return GlobalScope.async {
            val response = repository.get(requestParams)
            val models = response.casts.map { translator.translate(it) }
            return@async models
        }
    }

}