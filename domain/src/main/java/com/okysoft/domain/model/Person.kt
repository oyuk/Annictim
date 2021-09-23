package com.okysoft.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
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
) : Parcelable {
    @Parcelize
    data class Prefecture(
        val id: Int?,
        val name: String?
    ) : Parcelable
}