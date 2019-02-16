package com.okysoft.annictim.api

import com.apollographql.apollo.ApolloClient
import com.okysoft.annictim.api.model.response.User
import com.okysoft.annictim.api.model.response.UsersResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class UserServiceImpl @Inject constructor(
    private val client: ApolloClient,
    @Named("coroutines") retrofit: Retrofit
        ): AnnictService.User {

    private val retrofitClient = retrofit.create(AnnictService.User::class.java)

//    override fun getMe(): Single<Result<User>> {
//        return Rx2Apollo.from(client.query(Me())).map {
//            it.data()?.viewer()?.let {
//                return@map Result.success()
////                return@map Result.success(User(
////                        name = it.name(),
////                        userName = it.username()
////                ))
//            }
//            return@map Result.failure<User>("hoge", Throwable())
//        }.single(Result.inProgress())
//    }

    override fun getMe(accessToken: String): Deferred<User> {
        return retrofitClient.getMe(accessToken)
    }

    override fun get(userIds: String): Deferred<UsersResponse> {
        return retrofitClient.get(userIds)

    }

}
