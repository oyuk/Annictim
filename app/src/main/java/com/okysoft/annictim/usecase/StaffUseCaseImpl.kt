package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.Staff
import com.okysoft.annictim.infra.api.model.request.StaffRequestParams
import com.okysoft.annictim.infra.api.repository.StaffRepository
import com.okysoft.annictim.translator.StaffTranslator
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.rx2.rxSingle

class StaffUseCaseImpl (
    private val repository: StaffRepository,
    private val translator: StaffTranslator
): StaffUseCase {

    override fun get(requestParams: StaffRequestParams): Single<List<Staff>> {
        return GlobalScope.rxSingle {
            val response = repository.get(requestParams).await()
            val models = response.staffs.map { translator.translate(it) }
            return@rxSingle models
        }
    }

}