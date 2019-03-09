package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Cast
import com.okysoft.annictim.infra.api.model.request.CastRequestParams
import kotlinx.coroutines.Deferred

interface CastUseCase {

    fun get(requestParams: CastRequestParams): Deferred<List<Cast>>

}
