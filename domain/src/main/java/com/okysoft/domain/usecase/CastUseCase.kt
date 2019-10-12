package com.okysoft.domain.usecase

import com.okysoft.domain.model.Cast
import kotlinx.coroutines.Deferred

interface CastUseCase {

    fun get(requestParams: com.okysoft.data.CastRequestParams): Deferred<List<Cast>>

}
