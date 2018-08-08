package com.okysoft.annictim.API.Model.Response

data class OauthResponseModel(
        val accessToken: String,
        private val tokenType: String,
        private val scope: String,
        private val createdAt: String
)