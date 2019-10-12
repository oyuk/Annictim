package com.okysoft.domain.usecase

import com.okysoft.domain.model.Staff
import com.okysoft.domain.translator.StaffTranslator
import com.okysoft.infra.repository.StaffRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class StaffUseCaseImpl (
    private val repository: StaffRepository,
    private val translator: StaffTranslator
): StaffUseCase {

    override fun get(requestParams: com.okysoft.data.StaffRequestParams): Deferred<List<Staff>> {
        return GlobalScope.async {
            val response = repository.get(requestParams).await()
            val models = response.staffs.map { translator.translate(it) }
            return@async models
        }
    }

}