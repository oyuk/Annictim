package com.okysoft.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val birthday: String? = null,
    val bloodType: String? = null,
    val castsCount: Int? = null,
    val favoritePeopleCount: Int? = null,
    val genderText: String? = null,
    val height: Int? = null,
    val id: Int,
    val name: String? = null,
    val nameEn: String? = null,
    val nameKana: String? = null,
    val nickname: String? = null,
    val nicknameEn: String? = null,
    val prefecture: Prefecture? = null,
    val staffsCount: Int? = null,
    val twitterUsername: String? = null,
    val twitterUsernameEn: String? = null,
    val url: String? = null,
    val urlEn: String? = null,
    val wikipediaUrl: String? = null,
    val wikipediaUrlEn: String? = null
) : Parcelable {
    @Parcelize
    data class Prefecture(
        val id: Int? = null,
        val name: String? = null
    ) : Parcelable
}