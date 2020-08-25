package com.okysoft.infra.impl

import com.okysoft.infra.response.UserResponse
import com.okysoft.infra.response.UsersResponse
import com.okysoft.infra.AnnictService
import com.okysoft.infra.repository.AuthRepository
import com.okysoft.infra.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authRepository: AuthRepository,
    retrofit: Retrofit
): UserRepository {

    private val retrofitClient = retrofit.create(AnnictService.User::class.java)

    override suspend fun getMe(): Flow<UserResponse> {
        return flow {
         emit(retrofitClient.getMe(authRepository.getStoredAccessToken()))
        }
    }

    override suspend fun get(userId: Int): Flow<UsersResponse> {
        return flow {
            emit(retrofitClient.get(userId.toString()))
        }
    }

}
