package com.okysoft.annictim.infra.api.model.request

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class ProgramRequestParams(
    val fields: String = "",
    val page: Int = 1,
    val sort_started_at: String = "desc"
): PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelProgramRequestParams.CREATOR
    }

    fun toParams(): Map<String, String>
        = mutableMapOf<String, String>().apply {
        this["fields"] = fields
        this["page"] = page.toString()
        this["sort_started_at"] = sort_started_at
    }

}