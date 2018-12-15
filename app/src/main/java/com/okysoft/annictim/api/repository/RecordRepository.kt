package com.okysoft.annictim.api.repository

import com.okysoft.annictim.api.AnnictService
import com.okysoft.annictim.api.model.response.Record
import com.okysoft.annictim.Result
import io.reactivex.Single
import javax.inject.Inject

class RecordRepository @Inject constructor(private val service: AnnictService.Record) {

    fun get(episodeId: Int): Single<Result<List<Record>>> {
        return service.get(episodeId)
                .map { Result.success(it.records) }
                .onErrorReturn { Result.failure<List<Record>>(it.toString(), it) }
    }

}