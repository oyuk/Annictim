package com.okysoft.annictim.API.Model.Request


data class OauthRequestModel(
        private val clientId: String,
        private val clientSecret: String,
        private val grantType: String,
        private val redirectUri: String,
        private val code: String
)