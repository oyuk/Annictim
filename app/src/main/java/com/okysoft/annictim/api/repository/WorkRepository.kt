package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.Work
import com.okysoft.annictim.api.model.response.WorksResponse
import com.okysoft.annictim.api.model.WorksRequestParamModel
import com.okysoft.annictim.presentation.MeFilterStatus
import com.okysoft.annictim.Result
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
        return latest(requestModel.worksRequestType.toParams(), requestModel.fields, page)
    }

    fun latest(season: String, fields: WorksRequestParamModel.Fields, page: Int = 1): Single<Result<List<Work>>> {
        val params = when(fields) {
            WorksRequestParamModel.Fields.All -> { null }
            WorksRequestParamModel.Fields.Feed -> { fields.toParams() }
        }
        return service._latest(season, params, page).toWorkResults()
    }

    fun me(filter: MeFilterStatus, page: Int): Single<Result<List<Work>>> {
        return service.me(filter.toString(), page).toWorkResults()
    }

}
