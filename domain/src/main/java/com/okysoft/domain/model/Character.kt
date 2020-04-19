package com.okysoft.domain.model


data class Character(
    val age: String?,
    val ageEn: String?,
    val birthday: String?,
    val birthdayEn: String?,
    val bloodType: String?,
    val bloodTypeEn: String?,
    val description: String?,
    val descriptionEn: String?,
    val descriptionSource: String?,
    val descriptionSourceEn: String?,
    val favoriteCharactersCount: Int?,
    val height: String?,
    val heightEn: String?,
    val id: Int?,
    val kind: String?,
    var name: String?,
    val nameEn: String?,
    val nameKana: String?,
    val nationality: String?,
    val nationalityEn: String?,
    val nickname: String?,
    val nicknameEn: String?,
    val occupation: String?,
    val occupationEn: String?,
    val series: Series?,
    val weight: String?,
    val weightEn: String?
)