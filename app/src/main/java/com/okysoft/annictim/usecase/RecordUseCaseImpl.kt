package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Record
import com.okysoft.annictim.infra.api.repository.RecordRepository
import com.okysoft.annictim.translator.RecordTranslator
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.rx2.rxSingle

class RecordUseCaseImpl (
    private val repository: RecordRepository,
    private val translator: RecordTranslator
): RecordUseCase {

    override fun get(episodeId: Int): Single<List<Record>> {
        return GlobalScope.rxSingle {
            val response = repository.get(episodeId).await()
            val models = response.records.map { translator.translate(it) }
            return@rxSingle models
        }
    }

}