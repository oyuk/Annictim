package com.okysoft.domain.usecase

import com.okysoft.domain.model.Episode
import com.okysoft.domain.translator.EpisodeTranslator
import com.okysoft.infra.repository.EpisodeRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EpisodeUseCaseImpl (
    private val repository: EpisodeRepository,
    private val translator: EpisodeTranslator
): EpisodeUseCase {

    override suspend fun get(workId: Int): Flow<List<Episode>> {
        return repository.get(workId, "asc")
            .map { response ->
                response.episodes.map { translator.translate(it) }
            }
    }

}