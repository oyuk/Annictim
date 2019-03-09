package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Episode
import com.okysoft.annictim.infra.api.repository.EpisodeRepository
import com.okysoft.annictim.translator.EpisodeTranslator
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EpisodeUseCaseImpl (
    private val repository: EpisodeRepository,
    private val translator: EpisodeTranslator
): EpisodeUseCase {

    override fun get(workId: Int): Deferred<List<Episode>> {
        return GlobalScope.async {
            val response = repository.get(workId).await()
            val models = response.episodes.map { translator.translate(it) }
            return@async models
        }
    }

}