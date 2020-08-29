package com.okysoft.domain.translator

import com.okysoft.domain.model.User
import com.okysoft.infra.fragment.Work
import com.okysoft.infra.response.UserResponse

class UserTranslator: Translator<UserResponse, User> {

    override fun translate(response: UserResponse): User {
        return User(
            id = response.id,
            avatarUrl = response.avatarUrl,
            name = response.name,
            onHoldCount = response.onHoldCount,
            recordsCount = response.recordsCount,
            stopWatchingCount = response.stopWatchingCount,
            wannaWatchCount = response.wannaWatchCount,
            watchedCount = response.watchedCount,
            watchingCount = response.watchingCount,
            username = response.username
        )
    }

}