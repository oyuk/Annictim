package com.okysoft.domain.usecase

import com.okysoft.domain.model.WorkDetail
import com.okysoft.domain.translator.WorkDetailTranslator
import com.okysoft.infra.repository.WorkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WorkDetailUseCaseImpl(
    private val repository: WorkRepository,
    private val translator: WorkDetailTranslator
): WorkDetailUseCase {

    override fun get(id: Int): Flow<WorkDetail> {
        return repository.getWork(id)
            .map { response ->
                translator.translate(response)
            }
    }
}