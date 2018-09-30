package com.okysoft.annictim.API.Repository

import com.okysoft.annictim.API.AnnictService
import com.okysoft.annictim.API.Model.Response.Record
import com.okysoft.annictim.Result
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RecordRepository @Inject constructor(private val service: AnnictService.Record) {

    fun get(episodeId: Int): Single<Result<List<Record>>> {
        return service.get(episodeId)
                .map { Result.success(it.records) }
                .onErrorReturn { Result.failure<List<Record>>(it.toString(), it) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}