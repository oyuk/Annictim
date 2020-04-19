package com.okysoft.domain.usecase

import com.okysoft.domain.model.Staff
import com.okysoft.domain.translator.StaffTranslator
import com.okysoft.infra.repository.StaffRepository
import kotlinx.coroutines.*

class StaffUseCaseImpl (
    private val repository: StaffRepository,
    private val translator: StaffTranslator
): StaffUseCase {

    override suspend fun get(requestParams: com.okysoft.data.StaffRequestParams): List<Staff> {
        return withContext(Dispatchers.IO) {
            val response = repository.get(requestParams)
            val models = response.staffs.map { translator.translate(it) }
            return@withContext models
        }
    }

}