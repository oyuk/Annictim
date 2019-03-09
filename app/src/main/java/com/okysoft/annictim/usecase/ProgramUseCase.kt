package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Program
import com.okysoft.annictim.presentation.program.ProgramRequestParams
import io.reactivex.Single

interface ProgramUseCase {

    fun get(requestParams: ProgramRequestParams): Single<List<Program>>

}
