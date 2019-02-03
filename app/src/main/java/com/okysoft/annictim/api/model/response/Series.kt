package com.okysoft.annictim.api.model.response
import com.google.gson.annotations.SerializedName


data class Series(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("name_en")
    val nameEn: String?,
    @SerializedName("name_ro")
    val nameRo: String?
)