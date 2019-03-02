package com.okysoft.annictim.infra.api.repository

import android.content.Context
import com.okysoft.annictim.infra.KeyStoreManager
import com.okysoft.annictim.infra.SharedPreferenceManager

interface AuthRepository {
    fun putAccessToken(accessToken: String)
    fun getStoredAccessToken(): String
    fun deleteStoredAccessToken()
}

class AuthRepositoryImpl(private val keyStoreManager: KeyStoreManager, context: Context):
        AuthRepository,
        SharedPreferenceManager("access_token", context){

    private val KEY_ACCESS_TOKEN = "ACCESS_TOKEN"

    override fun putAccessToken(accessToken: String) {
        val encryptedAccessToken = keyStoreManager.encrypt(accessToken.toByteArray())
        saveByteArray(KEY_ACCESS_TOKEN, encryptedAccessToken)
    }

    override fun getStoredAccessToken(): String {
        val encryptedAccessToken = getByteArray(KEY_ACCESS_TOKEN)
        return String(keyStoreManager.decrypt(encryptedAccessToken))
    }

    override fun deleteStoredAccessToken() {
        remove(KEY_ACCESS_TOKEN)
    }

}