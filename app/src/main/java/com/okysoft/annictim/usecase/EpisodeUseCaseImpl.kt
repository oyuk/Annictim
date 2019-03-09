package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Episode
import com.okysoft.annictim.infra.api.repository.EpisodeRepository
import com.okysoft.annictim.translator.EpisodeTranslator
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.rx2.rxSingle

class EpisodeUseCaseImpl (
    private val repository: EpisodeRepository,
    private val translator: EpisodeTranslator
): EpisodeUseCase {

    override fun get(workId: Int): Single<List<Episode>> {
        return GlobalScope.rxSingle {
            val response = repository.get(workId).await()
            val models = response.episodes.map { translator.translate(it) }
            return@rxSingle models
        }
    }

}