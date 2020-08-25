package com.okysoft.infra.repository

import com.okysoft.infra.response.UserResponse
import com.okysoft.infra.response.UsersResponse
import com.okysoft.infra.AnnictService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface UserRepository {

    suspend fun getMe(): Flow<UserResponse>

    suspend fun get(userId: Int): Flow<UsersResponse>

}