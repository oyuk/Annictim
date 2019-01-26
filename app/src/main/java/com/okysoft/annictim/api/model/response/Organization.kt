package com.okysoft.annictim.api.model.response
import com.google.gson.annotations.SerializedName


data class Organization(
    @SerializedName("favorite_organizations_count")
    val favoriteOrganizationsCount: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_en")
    val nameEn: String,
    @SerializedName("name_kana")
    val nameKana: String,
    @SerializedName("staffs_count")
    val staffsCount: Int,
    @SerializedName("twitter_username")
    val twitterUsername: String,
    @SerializedName("twitter_username_en")
    val twitterUsernameEn: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("url_en")
    val urlEn: String,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String,
    @SerializedName("wikipedia_url_en")
    val wikipediaUrlEn: String
)