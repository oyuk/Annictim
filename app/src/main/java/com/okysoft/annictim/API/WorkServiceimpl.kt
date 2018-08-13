package com.okysoft.annictim.API

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.okysoft.annictim.API.Model.Response.Work
import com.okysoft.annictim.ApolloApi.Latest
import com.okysoft.annictim.Result
import com.okysoft.annictim.Result.Companion.inProgress
import io.reactivex.Single
import javax.inject.Inject

class WorkServiceImpl @Inject constructor(private val client: ApolloClient): AnnictService.Work {

    override fun latest(): Single<Result<List<Work>>> {
        return Rx2Apollo.from(client.query(Latest("2017-spring")))
                .map {
                    val edges = it.data()?.searchWorks()?.edges()
                            ?: return@map Result.failure<List<Work>>("",Throwable())
                    val s = edges.filter { it.node() != null }.map {
                        return@map Work(
                                id = it.node()!!.id(),
                                title = it.node()!!.title()
                        )
                    }
                    return@map Result.success(s)
                }.single(inProgress())
    }

}