package com.okysoft.annictim.API

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.API.Model.Response.WorksResponse
import com.okysoft.annictim.ApolloApi.Latest
import com.okysoft.annictim.Result
import com.okysoft.annictim.Result.Companion.inProgress
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class WorkServiceImpl @Inject constructor(
        private val client: ApolloClient,
        retrofit: Retrofit
        ): AnnictService.Works {

    private val meClient = retrofit.create(AnnictService.Work.Me::class.java)

    override fun latest(season: String): Single<Result<List<Work>>> {
        return Rx2Apollo.from(client.query(Latest(season)))
                .map {
                    val edges = it.data()?.searchWorks()?.edges()
                            ?: return@map Result.failure<List<Work>>("",Throwable())
                    val s = edges.filter { it.node() != null }.map {
                        return@map Work(
                                id = it.node()!!.id(),
                                title = it.node()!!.title(),
                                watchersCount = it.node()!!.watchersCount(),
                                imageUrl = it.node()!!.image()?.recommendedImageUrl() ?: ""
                        )
                    }
                    return@map Result.success(s)
                }.single(inProgress())
    }

    override fun me(filterStatus: String, page: Int): Single<WorksResponse> {
        return meClient.me(filterStatus, page)
    }

}