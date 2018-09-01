package com.okysoft.annictim.API.Repository

import com.okysoft.annictim.API.AnnictService
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.Presentation.MeFilterStatus
import com.okysoft.annictim.Result
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WorkRepository @Inject constructor(private val service: AnnictService.Works) {

    fun latest(season: String): Single<Result<List<Work>>> {
        return service.latest(season)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun me(filter: MeFilterStatus, page: Int): Single<Result<List<Work>>> {
        return service.me(filter.toString(), page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}