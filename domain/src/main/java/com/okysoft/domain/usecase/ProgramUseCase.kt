package com.okysoft.domain.usecase

import com.okysoft.domain.model.Program
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow

interface ProgramUseCase {

    suspend fun get(requestParams: com.okysoft.data.ProgramRequestParams): Flow<List<Program>>

}
