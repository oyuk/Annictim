package com.okysoft.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Character(
    val age: String? = null,
    val ageEn: String? = null,
    val birthday: String? = null,
    val birthdayEn: String? = null,
    val bloodType: String? = null,
    val bloodTypeEn: String? = null,
    val description: String? = null,
    val descriptionEn: String? = null,
    val descriptionSource: String? = null,
    val descriptionSourceEn: String? = null,
    val favoriteCharactersCount: Int? = null,
    val height: String? = null,
    val heightEn: String? = null,
    val id: Int,
    val kind: String? = null,
    var name: String,
    val nameEn: String? = null,
    val nameKana: String? = null,
    val nationality: String? = null,
    val nationalityEn: String? = null,
    val nickname: String? = null,
    val nicknameEn: String? = null,
    val occupation: String? = null,
    val occupationEn: String? = null,
    val series: Series? = null,
    val weight: String? = null,
    val weightEn: String? = null
): Parcelable