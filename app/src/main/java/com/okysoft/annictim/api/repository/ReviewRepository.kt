package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.Review
import com.okysoft.annictim.Result
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReviewRepository @Inject constructor(private val service: AnnictService.Review) {

    fun get(workId: Int): Single<Result<List<Review>>> {
        return service.get(workId)
                .map { Result.success(it.reviews) }
                .onErrorReturn { Result.failure<List<Review>>(it.toString(), it) }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

}