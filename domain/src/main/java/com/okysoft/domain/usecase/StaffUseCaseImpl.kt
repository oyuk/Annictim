package com.okysoft.domain.usecase

import com.okysoft.domain.model.Staff
import com.okysoft.domain.translator.StaffTranslator
import com.okysoft.infra.repository.StaffRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StaffUseCaseImpl (
    private val repository: StaffRepository,
    private val translator: StaffTranslator
): StaffUseCase {

    override suspend fun get(requestParams: com.okysoft.data.StaffRequestParams): Flow<List<Staff>> {
        return repository.get(requestParams)
            .map { response ->
                response.staffs.map { translator.translate(it) }
            }
    }

}
