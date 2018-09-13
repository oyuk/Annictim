package com.okysoft.annictim.API

import com.apollographql.apollo.ApolloClient
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.API.Model.Response.WorksResponse
import com.okysoft.annictim.Result
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class WorkServiceImpl @Inject constructor(
        private val client: ApolloClient,
        retrofit: Retrofit
        ): AnnictService.Works {

    private val meClient = retrofit.create(AnnictService.Work.Me::class.java)

    override fun latest(season: String): Single<Result<List<Work>>> {
        return Single.just(Result.success(listOf<Work>()))
        //TODO: あとでApolloからretrofitに変更する
//        return Rx2Apollo.from(client.query(Latest(season)))
//                .map {
//                    val edges = it.data()?.searchWorks()?.edges()
//                            ?: return@map Result.failure<List<Work>>("",Throwable())
//                    val s = edges.filter { it.node() != null }.map {
//                        return@map Work(
//                                id = it.node()!!.id(),
//                                title = it.node()!!.title(),
//                                watchersCount = it.node()!!.watchersCount(),
//                                imageUrl = it.node()!!.image()?.recommendedImageUrl() ?: ""
//                        )
//                    }
//                    return@map Result.success(s)
//                }.single(inProgress())
    }

    override fun me(filterStatus: String, page: Int): Single<WorksResponse> {
        return meClient.me(filterStatus, page)
    }

}