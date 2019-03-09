package com.okysoft.annictim.usecase

import com.okysoft.annictim.translator.WorkTranslator
import com.okysoft.annictim.domain.Work
import com.okysoft.annictim.infra.api.model.request.WorkRequestParams
import com.okysoft.annictim.infra.api.repository.WorkRepository
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.rx2.rxSingle

interface WorkUseCase {

    fun get(requestParams: WorkRequestParams): Single<List<Work>>


    fun me(requestParams: WorkRequestParams): Single<List<Work>>

}

class WorkUseCaseImpl (
    private val repository: WorkRepository,
    private val translator: WorkTranslator
): WorkUseCase {

    override fun get(requestParams: WorkRequestParams): Single<List<Work>> {
        return GlobalScope.rxSingle {
            val response = repository.get(requestParams).await()
            val models = response.works.map { translator.translate(it) }
            return@rxSingle models
        }
    }

    override fun me(requestParams: WorkRequestParams): Single<List<Work>> {
        return GlobalScope.rxSingle {
            val response = repository.me(requestParams).await()
            val models = response.works.map { translator.translate(it) }
            return@rxSingle models
        }
    }

}