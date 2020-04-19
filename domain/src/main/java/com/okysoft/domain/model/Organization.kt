package com.okysoft.domain.model

data class Organization(
    val favoriteOrganizationsCount: Int,
    val id: Int,
    val name: String,
    val nameEn: String,
    val nameKana: String,
    val staffsCount: Int,
    val twitterUsername: String,
    val twitterUsernameEn: String,
    val url: String,
    val urlEn: String,
    val wikipediaUrl: String,
    val wikipediaUrlEn: String
)