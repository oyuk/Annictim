package com.okysoft.data
import com.google.gson.annotations.SerializedName


data class StaffResponse(
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
    val workResponse: WorkResponse?,
    @SerializedName("organization")
    val organization: Organization?
)