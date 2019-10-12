package com.okysoft.domain.usecase

import com.okysoft.domain.model.Program
import com.okysoft.domain.translator.ProgramTranslator
import com.okysoft.infra.repository.ProgramRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ProgramUseCaseImpl (
    private val repository: ProgramRepository,
    private val translator: ProgramTranslator
): ProgramUseCase {

    override fun get(requestParams: com.okysoft.data.ProgramRequestParams): Deferred<List<Program>> {
        return GlobalScope.async {
            val response = repository.get(requestParams).await()
            val models = response.programs.map { translator.translate(it) }
            return@async models
        }
    }

}