package com.okysoft.domain.usecase

import com.okysoft.domain.model.User
import com.okysoft.domain.translator.UserTranslator
import com.okysoft.infra.repository.UserRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class UserUseCaseImpl (
    private val repository: UserRepository,
    private val translator: UserTranslator
): UserUseCase {

    override fun getMe(): Deferred<User> {
        return GlobalScope.async {
            val response = repository.getMe().await()
            val model = translator.translate(response)
            return@async model
        }
    }

    override fun get(userId: Int): Deferred<List<User>> {
        return GlobalScope.async {
            val response = repository.get(userId).await()
            val models = response.users.map { translator.translate(it) }
            return@async models
        }
    }

}