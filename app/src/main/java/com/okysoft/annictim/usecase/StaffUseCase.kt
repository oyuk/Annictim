package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Staff
import com.okysoft.annictim.infra.api.model.request.StaffRequestParams
import io.reactivex.Single

interface StaffUseCase {

    fun get(requestParams: StaffRequestParams): Single<List<Staff>>

}
