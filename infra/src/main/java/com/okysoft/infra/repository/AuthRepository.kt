package com.okysoft.infra.repository

interface AuthRepository {
    fun putAccessToken(accessToken: String)
    fun getStoredAccessToken(): String
    fun deleteStoredAccessToken()
}