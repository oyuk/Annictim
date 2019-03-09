package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Staff
import com.okysoft.annictim.infra.api.model.request.StaffRequestParams
import com.okysoft.annictim.infra.api.repository.StaffRepository
import com.okysoft.annictim.translator.StaffTranslator
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class StaffUseCaseImpl (
    private val repository: StaffRepository,
    private val translator: StaffTranslator
): StaffUseCase {

    override fun get(requestParams: StaffRequestParams): Deferred<List<Staff>> {
        return GlobalScope.async {
            val response = repository.get(requestParams).await()
            val models = response.staffs.map { translator.translate(it) }
            return@async models
        }
    }

}