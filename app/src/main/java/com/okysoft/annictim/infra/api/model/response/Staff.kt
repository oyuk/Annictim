package com.okysoft.annictim.infra.api.model.response
import com.google.gson.annotations.SerializedName


data class Staff(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("name_en")
    val nameEn: String?,
    @SerializedName("role_other")
    val roleOther: String?,
    @SerializedName("role_other_en")
    val roleOtherEn: String?,
    @SerializedName("role_text")
    val roleText: String,
    @SerializedName("sort_number")
    val sortNumber: Int?,
    @SerializedName("work")
    val work: Work?,
    @SerializedName("organization")
    val organization: Organization?
)