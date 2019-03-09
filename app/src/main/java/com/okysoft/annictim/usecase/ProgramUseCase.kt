package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Program
import com.okysoft.annictim.infra.api.model.request.ProgramRequestParams
import kotlinx.coroutines.Deferred

interface ProgramUseCase {

    fun get(requestParams: ProgramRequestParams): Deferred<List<Program>>

}
