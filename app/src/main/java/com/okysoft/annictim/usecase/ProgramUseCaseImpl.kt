package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Program
import com.okysoft.annictim.infra.api.repository.ProgramRepository
import com.okysoft.annictim.infra.api.model.request.ProgramRequestParams
import com.okysoft.annictim.translator.ProgramTranslator
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.rx2.rxSingle

class ProgramUseCaseImpl (
    private val repository: ProgramRepository,
    private val translator: ProgramTranslator
): ProgramUseCase {

    override fun get(requestParams: ProgramRequestParams): Single<List<Program>> {
        return GlobalScope.rxSingle {
            val response = repository.get(requestParams).await()
            val models = response.programs.map { translator.translate(it) }
            return@rxSingle models
        }
    }

}