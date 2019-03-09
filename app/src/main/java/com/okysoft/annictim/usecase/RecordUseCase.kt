package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Record
import io.reactivex.Single

interface RecordUseCase {

    fun get(workId: Int): Single<List<Record>>

}
