package com.okysoft.annictim.infra.api.model.request


data class OauthRequestModel(
        private val clientId: String,
        private val clientSecret: String,
        private val grantType: String,
        private val redirectUri: String,
        private val code: String
)