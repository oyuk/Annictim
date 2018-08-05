package com.okysoft.annictim.API.Model.Request


data class OauthRequestModel(
        private val clientID: String,
        private val clientSecret: String,
        private val grandType: String,
        private val redirectURL: String,
        private val code: String
)