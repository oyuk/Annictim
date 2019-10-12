package com.okysoft.infra.impl

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class UserServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.User {

    private val retrofitClient = retrofit.create(AnnictService.User::class.java)

    override fun getMe(accessToken: String): Deferred<com.okysoft.data.UserResponse> {
        return retrofitClient.getMe(accessToken)
    }

    override fun get(userIds: String): Deferred<com.okysoft.data.UsersResponse> {
        return retrofitClient.get(userIds)
    }

}
