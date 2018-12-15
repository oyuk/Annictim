package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.Episode
import com.okysoft.annictim.Result
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EpisodeRepository @Inject constructor(private val service: AnnictService.Episode) {

    fun get(workId: Int): Single<Result<List<Episode>>> {
        return service.get(workId, "asc")
                .map { Result.success(it.episodes) }
                .onErrorReturn { Result.failure<List<Episode>>(it.toString(), it) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}