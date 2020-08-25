package com.okysoft.domain.usecase

import com.okysoft.domain.model.Program
import com.okysoft.domain.translator.ProgramTranslator
import com.okysoft.infra.repository.ProgramRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProgramUseCaseImpl (
    private val repository: ProgramRepository,
    private val translator: ProgramTranslator
): ProgramUseCase {

    override suspend fun get(requestParams: com.okysoft.data.ProgramRequestParams): Flow<List<Program>> {
        return repository.get(requestParams)
            .map { response ->
                response.programs.map { translator.translate(it) }
            }
    }

}