package com.okysoft.infra.impl

import com.okysoft.infra.AnnictService
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import javax.inject.Inject

class UserServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.User {

    private val retrofitClient = retrofit.create(AnnictService.User::class.java)

    override suspend fun getMe(accessToken: String): com.okysoft.data.UserResponse {
        return retrofitClient.getMe(accessToken)
    }

    override suspend fun get(userIds: String): com.okysoft.data.UsersResponse {
        return retrofitClient.get(userIds)
    }

}
