package com.okysoft.infra.impl

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toFlow
import com.okysoft.data.WorkRequestParams
import com.okysoft.infra.AnnictService
import com.okysoft.infra.WorkQuery
import com.okysoft.infra.fragment.Work
import com.okysoft.infra.repository.WorkRepository
import com.okysoft.infra.response.WorksResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Retrofit
import javax.inject.Inject

class WorkRepositoryImpl @Inject constructor(retrofit: Retrofit, private val apolloClient: ApolloClient): WorkRepository {

    private val retrofitClient = retrofit.create(AnnictService.Work::class.java)
    private val meClient = retrofit.create(AnnictService.Work.Me::class.java)

    override suspend fun get(requestParams: WorkRequestParams): Flow<WorksResponse> {
        return flow {
            emit(retrofitClient.get(requestParams.toParams()))
        }
    }

    override suspend fun me(requestParamModel: WorkRequestParams): Flow<WorksResponse> {
        return flow {
            emit(meClient.me(requestParamModel.toParams()))
        }
    }

    override suspend fun request(requestParams: WorkRequestParams, page: Int): Flow<WorksResponse> {
        return flow {
            when (requestParams.type) {
                WorkRequestParams.Type.Me -> me(requestParams.copy(page = page))
                WorkRequestParams.Type.Works -> get(requestParams.copy(page = page))
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun getWork(id: Int): Flow<Work> {
        return apolloClient.query(WorkQuery(id)).toFlow()
            .map {
                val work = it.data?.searchWorks?.nodes?.let { l ->
                    l.mapNotNull { node -> node?.fragments?.work }
                }?.firstOrNull()
                if (work != null) {
                    return@map work
                }
                throw NullPointerException()
            }
    }

}
