package com.okysoft.data

data class OauthResponseModel(
        val accessToken: String,
        private val tokenType: String,
        private val scope: String,
        private val createdAt: String
)