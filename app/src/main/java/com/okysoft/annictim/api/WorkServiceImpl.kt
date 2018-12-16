package com.okysoft.annictim.api

import com.apollographql.apollo.ApolloClient
import com.okysoft.annictim.api.model.response.Work
import com.okysoft.annictim.api.model.response.WorksResponse
import com.okysoft.annictim.Result
import com.okysoft.annictim.Result.Companion.success
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class WorkServiceImpl @Inject constructor(
        private val client: ApolloClient,
        retrofit: Retrofit
        ): AnnictService.Works {

    private val retrofitClient = retrofit.create(AnnictService.Work::class.java)
    private val meClient = retrofit.create(AnnictService.Work.Me::class.java)

    override fun get(filterIds: String): Single<WorksResponse> {
        return retrofitClient.get(filterIds)
    }

    override fun _latest(season: String, fields: String?, page: Int): Single<WorksResponse> {
        return retrofitClient._latest(season, fields, page)
    }

    override fun latest(season: String): Single<Result<List<Work>>> {
        return Single.just(success(listOf()))
//        return Rx2Apollo.from(client.query(Latest(season)))
//                .map {
//                    val edges = it.data()?.searchWorks()?.edges()
//                            ?: return@map Result.failure<List<Work>>("",Throwable())
//                    val s = edges.filter { it.node() != null }.map {
//                        return@map Work(
//                                id = it.node()!!.id().toInt(),
//                                title = it.node()!!.title(),
//                                watchersCount = it.node()!!.watchersCount()
////                                imageUrl = it.node()!!.image()?.recommendedImageUrl() ?: ""
//                        )
//                    }
//                    return@map Result.success(s)
//                }.single(inProgress())
    }

    override fun me(filterStatus: String, page: Int): Single<WorksResponse> {
        return meClient.me(filterStatus, page)
    }

}