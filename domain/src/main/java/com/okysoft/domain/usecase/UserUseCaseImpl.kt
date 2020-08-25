package com.okysoft.domain.usecase

import com.okysoft.domain.model.User
import com.okysoft.domain.translator.UserTranslator
import com.okysoft.infra.repository.UserRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserUseCaseImpl (
    private val repository: UserRepository,
    private val translator: UserTranslator
): UserUseCase {

    override suspend fun getMe(): Flow<User> {
        return repository.getMe()
            .map { response ->
                translator.translate(response)
            }
    }


    override suspend fun get(userId: Int): Flow<List<User>> {
        return repository.get(userId)
            .map { response ->
                response.users.map { translator.translate(it) }
            }
    }

}