package com.okysoft.annictim.api.repository

import com.okysoft.annictim.Result
import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.WorksRequestParamModel
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

    fun get(id: Int): Single<Result<Work>> {
        return service.get("${id}")
                .map { Result.success(it.works.first()) }
                .onErrorReturn { Result.failure<Work>(it.toString(), it) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun latest(requestModel: WorksRequestParamModel, page: Int = 1): Single<Result<List<Work>>> {
        return search(requestModel, page)
    }

    fun search(requestParamModel: WorksRequestParamModel, page: Int = 1): Single<Result<List<Work>>> {
        val params = requestParamModel.toParams().toMutableMap()
        params["page"] = page.toString()
        return service.search(params).toWorkResults()
    }

    fun me(requestParamModel: WorksRequestParamModel, page: Int): Single<Result<List<Work>>> {
        val params = requestParamModel.toParams().toMutableMap()
        params["page"] = page.toString()
        return service.me(params).toWorkResults()
    }

}
