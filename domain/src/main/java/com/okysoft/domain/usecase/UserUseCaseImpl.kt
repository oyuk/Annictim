package com.okysoft.domain.usecase

import com.okysoft.domain.model.User
import com.okysoft.domain.translator.UserTranslator
import com.okysoft.infra.repository.UserRepository
import kotlinx.coroutines.*

class UserUseCaseImpl (
    private val repository: UserRepository,
    private val translator: UserTranslator
): UserUseCase {

    override suspend fun getMe(): User {
        return withContext(Dispatchers.IO) {
            val response = repository.getMe()
            val model = translator.translate(response)
            return@withContext model
        }
    }

    override suspend fun get(userId: Int): List<User> {
        return withContext(Dispatchers.IO) {
            val response = repository.get(userId)
            val models = response.users.map { translator.translate(it) }
            return@withContext models
        }
    }

}