package com.okysoft.annictim.infra.api.model.response

data class OauthResponseModel(
        val accessToken: String,
        private val tokenType: String,
        private val scope: String,
        private val createdAt: String
)