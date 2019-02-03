package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.request.WorkStatusRequestParams
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MeRepository @Inject constructor(private val service: AnnictService.Me) {

    fun updateStatus(statusRequestParams: WorkStatusRequestParams): Completable {
        return service.status(statusRequestParams.toParams())
            .subscribeOn(Schedulers.newThread())
    }

}