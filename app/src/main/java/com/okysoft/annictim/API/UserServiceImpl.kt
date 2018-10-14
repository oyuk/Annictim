package com.okysoft.annictim.API

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.okysoft.annictim.API.Model.Response.User
import com.okysoft.annictim.API.Model.Response.UsersResponse
import com.okysoft.annictim.ApolloApi.Me
import com.okysoft.annictim.Result
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class UserServiceImpl @Inject constructor(
        private val client: ApolloClient,
        retrofit: Retrofit
        ): AnnictService.User {

    private val retrofitClient = retrofit.create(AnnictService.User::class.java)

    override fun getMe(): Single<Result<User>> {
        return Rx2Apollo.from(client.query(Me())).map {
            it.data()?.viewer()?.let {
                return@map Result.success(User(
                        name = it.name(),
                        userName = it.username()
                ))
            }
            return@map Result.failure<User>("hoge", Throwable())
        }.single(Result.inProgress())
    }

    override fun get(userIds: String): Single<UsersResponse> {
        return retrofitClient.get(userIds)
    }

}
