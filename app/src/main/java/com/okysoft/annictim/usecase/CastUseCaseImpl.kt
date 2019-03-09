package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Cast
import com.okysoft.annictim.infra.api.model.request.CastRequestParams
import com.okysoft.annictim.infra.api.repository.CastRepository
import com.okysoft.annictim.translator.CastTranslator
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.rx2.rxSingle

class CastUseCaseImpl (
    private val repository: CastRepository,
    private val translator: CastTranslator
): CastUseCase {

    override fun get(requestParams: CastRequestParams): Single<List<Cast>> {
        return GlobalScope.rxSingle {
            val response = repository.get(requestParams).await()
            val models = response.casts.map { translator.translate(it) }
            return@rxSingle models
        }
    }

}