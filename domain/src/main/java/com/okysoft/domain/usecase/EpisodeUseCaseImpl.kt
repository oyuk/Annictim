package com.okysoft.domain.usecase

import com.okysoft.domain.model.Episode
import com.okysoft.domain.translator.EpisodeTranslator
import com.okysoft.infra.repository.EpisodeRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EpisodeUseCaseImpl (
    private val repository: EpisodeRepository,
    private val translator: EpisodeTranslator
): EpisodeUseCase {

    override fun get(workId: Int): Deferred<List<Episode>> {
        return GlobalScope.async {
            val response = repository.get(workId)
            val models = response.episodes.map { translator.translate(it) }
            return@async models
        }
    }

}