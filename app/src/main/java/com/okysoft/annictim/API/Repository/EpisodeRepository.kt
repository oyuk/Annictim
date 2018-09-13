package com.okysoft.annictim.API.Repository

import com.okysoft.annictim.API.AnnictService
import com.okysoft.annictim.API.Model.Response.Episode
import com.okysoft.annictim.Result
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val service: AnnictService.Episode) {

    fun get(workId: Int): Single<Result<List<Episode>>> {
        return service.get(workId)
                .map { Result.success(it) }
                .onErrorReturn { Result.failure<List<Episode>>(it.toString(), it) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}