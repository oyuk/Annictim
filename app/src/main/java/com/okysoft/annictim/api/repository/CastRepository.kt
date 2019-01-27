package com.okysoft.annictim.api.repository

import com.okysoft.annictim.Result
import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.Cast
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CastRepository @Inject constructor(private val service: AnnictService.Cast) {

    fun get(params: Map<String, String>): Single<Result<List<Cast>>> {
        return service.get(params)
            .map { Result.success(it.casts) }
            .onErrorReturn { Result.failure<List<Cast>>(it.toString(), it) }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

}