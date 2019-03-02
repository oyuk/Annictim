package com.okysoft.annictim.infra.api.model.response
import com.google.gson.annotations.SerializedName
data class Cast(
    @SerializedName("character")
    val character: Character,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_en")
    val nameEn: String,
    @SerializedName("person")
    val person: People,
    @SerializedName("sort_number")
    val sortNumber: Int,
    @SerializedName("work")
    val work: Work
)
