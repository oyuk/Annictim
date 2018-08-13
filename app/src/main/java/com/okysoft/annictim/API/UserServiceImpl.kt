package com.okysoft.annictim.API

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.okysoft.annictim.API.Model.Response.User
import com.okysoft.annictim.ApolloApi.MeQuery
import com.okysoft.annictim.Result
import io.reactivex.Single
import javax.inject.Inject

class UserServiceImpl @Inject constructor(private val client: ApolloClient): AnnictService.User {

    override fun getMe(): Single<Result<User>> {
        return Rx2Apollo.from(client.query(MeQuery())).map {
            it.data()?.viewer()?.let {
                return@map Result.success(User(
                        name = it.name(),
                        userName = it.username()
                ))
            }
            return@map Result.failure<User>("hoge", Throwable())
        }.single(Result.inProgress())
    }

}