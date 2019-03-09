package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Record
import io.reactivex.Single

interface RecordUseCase {

    fun get(episodeId: Int): Single<List<Record>>

}
