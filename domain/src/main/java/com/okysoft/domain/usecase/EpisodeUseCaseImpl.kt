package com.okysoft.domain.usecase

import com.okysoft.domain.model.Episode
import com.okysoft.domain.translator.EpisodeTranslator
import com.okysoft.infra.repository.EpisodeRepository
import kotlinx.coroutines.*

class EpisodeUseCaseImpl (
    private val repository: EpisodeRepository,
    private val translator: EpisodeTranslator
): EpisodeUseCase {

    override suspend fun get(workId: Int): List<Episode> {
        return withContext(Dispatchers.IO) {
            val response = repository.get(workId)
            val models = response.episodes.map { translator.translate(it) }
            return@withContext models
        }
    }

}