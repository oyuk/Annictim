package com.okysoft.annictim.api.repository

import com.okysoft.annictim.Result
import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.WorkRequestParams
import com.okysoft.annictim.api.model.response.Work
import com.okysoft.annictim.api.model.response.WorksResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

fun Single<WorksResponse>.toWorkResults(): Single<Result<List<Work>>> {
    return map { Result.success(it.works) }
            .onErrorReturn { Result.failure<List<Work>>(it.toString(), it) }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
}

class WorkRepository @Inject constructor(private val service: AnnictService.Works) {

    fun get(requestParams: WorkRequestParams): Single<Result<List<Work>>> {
        return service.get(requestParams.toParams())
                .map { Result.success(it.works) }
                .onErrorReturn { Result.failure<List<Work>>(it.toString(), it) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun me(requestParamModel: WorkRequestParams): Single<Result<List<Work>>> {
        return service.me(requestParamModel.toParams()).toWorkResults()
    }

}
