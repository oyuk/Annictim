package com.okysoft.annictim.api.repository

import com.okysoft.annictim.Result
import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.Staff
import com.okysoft.annictim.presentation.StaffRequestParams
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StaffRepository @Inject constructor(private val service: AnnictService.Staff) {

    fun get(requestParams: StaffRequestParams): Single<Result<List<Staff>>> {
        return service.get(requestParams.toParams())
            .map { Result.success(it.staffs) }
            .onErrorReturn { Result.failure<List<Staff>>(it.toString(), it) }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

}