package com.okysoft.data
import com.google.gson.annotations.SerializedName


data class Character(
    @SerializedName("age")
    val age: String?,
    @SerializedName("age_en")
    val ageEn: String?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("birthday_en")
    val birthdayEn: String?,
    @SerializedName("blood_type")
    val bloodType: String?,
    @SerializedName("blood_type_en")
    val bloodTypeEn: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("description_en")
    val descriptionEn: String?,
    @SerializedName("description_source")
    val descriptionSource: String?,
    @SerializedName("description_source_en")
    val descriptionSourceEn: String?,
    @SerializedName("favorite_characters_count")
    val favoriteCharactersCount: Int?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("height_en")
    val heightEn: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("kind")
    val kind: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("name_en")
    val nameEn: String?,
    @SerializedName("name_kana")
    val nameKana: String?,
    @SerializedName("nationality")
    val nationality: String?,
    @SerializedName("nationality_en")
    val nationalityEn: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("nickname_en")
    val nicknameEn: String?,
    @SerializedName("occupation")
    val occupation: String?,
    @SerializedName("occupation_en")
    val occupationEn: String?,
    @SerializedName("series")
    val series: Series?,
    @SerializedName("weight")
    val weight: String?,
    @SerializedName("weight_en")
    val weightEn: String?
)