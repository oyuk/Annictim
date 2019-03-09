package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Program
import com.okysoft.annictim.infra.api.model.request.ProgramRequestParams
import com.okysoft.annictim.infra.api.repository.ProgramRepository
import com.okysoft.annictim.translator.ProgramTranslator
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ProgramUseCaseImpl (
    private val repository: ProgramRepository,
    private val translator: ProgramTranslator
): ProgramUseCase {

    override fun get(requestParams: ProgramRequestParams): Deferred<List<Program>> {
        return GlobalScope.async {
            val response = repository.get(requestParams).await()
            val models = response.programs.map { translator.translate(it) }
            return@async models
        }
    }

}