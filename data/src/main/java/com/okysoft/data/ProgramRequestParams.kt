package com.okysoft.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProgramRequestParams(
    val fields: String = "",
    val page: Int = 1,
    val sort_started_at: String = "desc"
): Parcelable {

    fun toParams(): Map<String, String>
        = mutableMapOf<String, String>().apply {
        this["fields"] = fields
        this["page"] = page.toString()
        this["sort_started_at"] = sort_started_at
    }

}