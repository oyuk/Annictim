package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Cast
import com.okysoft.annictim.infra.api.model.request.CastRequestParams
import io.reactivex.Single

interface CastUseCase {

    fun get(requestParams: CastRequestParams): Single<List<Cast>>

}
