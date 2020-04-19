package com.okysoft.domain.usecase

import com.okysoft.domain.model.Program
import com.okysoft.domain.translator.ProgramTranslator
import com.okysoft.infra.repository.ProgramRepository
import kotlinx.coroutines.*

class ProgramUseCaseImpl (
    private val repository: ProgramRepository,
    private val translator: ProgramTranslator
): ProgramUseCase {

    override suspend fun get(requestParams: com.okysoft.data.ProgramRequestParams): List<Program> {
        return withContext(Dispatchers.IO) {
            val response = repository.get(requestParams)
            val models = response.programs.map { translator.translate(it) }
            return@withContext models
        }
    }

}