package com.okysoft.infra.impl

import com.okysoft.infra.EncryptedStore
import com.okysoft.infra.repository.AuthRepository
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(val dateStore: EncryptedStore): AuthRepository {

    private val KEY_ACCESS_TOKEN = "ACCESS_TOKEN"

    override fun putAccessToken(accessToken: String) {
        dateStore.save(KEY_ACCESS_TOKEN, accessToken)
    }

    override fun getStoredAccessToken(): String? {
        return dateStore.get(KEY_ACCESS_TOKEN)
    }

    override fun deleteStoredAccessToken() {
        dateStore.delete(KEY_ACCESS_TOKEN)
    }

}