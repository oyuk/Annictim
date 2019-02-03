package com.okysoft.annictim.api.model.response
import com.google.gson.annotations.SerializedName


data class People(
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("blood_type")
    val bloodType: String?,
    @SerializedName("casts_count")
    val castsCount: Int?,
    @SerializedName("favorite_people_count")
    val favoritePeopleCount: Int?,
    @SerializedName("gender_text")
    val genderText: String?,
//    @SerializedName("height")
//    val height: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("name_en")
    val nameEn: String?,
    @SerializedName("name_kana")
    val nameKana: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("nickname_en")
    val nicknameEn: String?,
    @SerializedName("prefecture")
    val prefecture: Prefecture?,
    @SerializedName("staffs_count")
    val staffsCount: Int?,
    @SerializedName("twitter_username")
    val twitterUsername: String?,
    @SerializedName("twitter_username_en")
    val twitterUsernameEn: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("url_en")
    val urlEn: String?,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String?,
    @SerializedName("wikipedia_url_en")
    val wikipediaUrlEn: String?
) {
    data class Prefecture(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?
    )
}