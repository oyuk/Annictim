package com.okysoft.domain.usecase

import com.okysoft.domain.model.Program
import kotlinx.coroutines.Deferred

interface ProgramUseCase {

    suspend fun get(requestParams: com.okysoft.data.ProgramRequestParams): List<Program>

}
