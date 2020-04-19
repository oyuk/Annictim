package com.okysoft.infra.impl

import com.okysoft.infra.response.UserResponse
import com.okysoft.infra.response.UsersResponse
import com.okysoft.infra.AnnictService
import retrofit2.Retrofit
import javax.inject.Inject

class UserServiceImpl @Inject constructor(retrofit: Retrofit): AnnictService.User {

    private val retrofitClient = retrofit.create(AnnictService.User::class.java)

    override suspend fun getMe(accessToken: String): UserResponse {
        return retrofitClient.getMe(accessToken)
    }

    override suspend fun get(userIds: String): UsersResponse {
        return retrofitClient.get(userIds)
    }

}
