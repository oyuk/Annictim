package com.okysoft.domain.translator

import com.okysoft.data.UserResponse
import com.okysoft.domain.model.User

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