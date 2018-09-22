package com.okysoft.annictim.API.Repository

import com.okysoft.annictim.API.AnnictService
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.API.Model.Response.WorksResponse
import com.okysoft.annictim.Presentation.MeFilterStatus
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

    fun latest(season: String): Single<Result<List<Work>>> {
        return service._latest(season).toWorkResults()
    }

    fun me(filter: MeFilterStatus, page: Int): Single<Result<List<Work>>> {
        return service.me(filter.toString(), page).toWorkResults()
    }

}