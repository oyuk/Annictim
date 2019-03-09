package com.okysoft.annictim.usecase

import com.okysoft.annictim.domain.User
import com.okysoft.annictim.infra.api.repository.UserRepository
import com.okysoft.annictim.translator.UserTranslator
import io.reactivex.Single
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.rx2.rxSingle

class UserUseCaseImpl (
    private val repository: UserRepository,
    private val translator: UserTranslator
): UserUseCase {

    override fun getMe(): Single<User> {
        return GlobalScope.rxSingle {
            val response = repository.getMe().await()
            val model = translator.translate(response)
            return@rxSingle model
        }
    }

    override fun get(userId: Int): Single<List<User>> {
        return GlobalScope.rxSingle {
            val response = repository.get(userId).await()
            val models = response.users.map { translator.translate(it) }
            return@rxSingle models
        }
    }

}