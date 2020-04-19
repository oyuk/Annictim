package com.okysoft.domain.model

data class People(
    val birthday: String?,
    val bloodType: String?,
    val castsCount: Int?,
    val favoritePeopleCount: Int?,
    val genderText: String?,
//    @SerializedName("height")
//    val height: Int?,
    val id: Int?,
    val name: String?,
    val nameEn: String?,
    val nameKana: String?,
    val nickname: String?,
    val nicknameEn: String?,
    val prefecture: Prefecture?,
    val staffsCount: Int?,
    val twitterUsername: String?,
    val twitterUsernameEn: String?,
    val url: String?,
    val urlEn: String?,
    val wikipediaUrl: String?,
    val wikipediaUrlEn: String?
) {
    data class Prefecture(
        val id: Int?,
        val name: String?
    )
}