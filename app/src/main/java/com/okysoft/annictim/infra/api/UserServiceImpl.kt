package com.okysoft.annictim.infra.api

import com.okysoft.annictim.infra.api.model.response.UserResponse
import com.okysoft.annictim.infra.api.model.response.UsersResponse
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class UserServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.User {

    private val retrofitClient = retrofit.create(AnnictService.User::class.java)

    override fun getMe(accessToken: String): Deferred<UserResponse> {
        return retrofitClient.getMe(accessToken)
    }

    override fun get(userIds: String): Deferred<UsersResponse> {
        return retrofitClient.get(userIds)

    }

}
