package com.okysoft.annictim.presentation

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class ProgramRequestParams(
    val fields: String = "",
    val page: Int = 1
): PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelProgramRequestParams.CREATOR
    }

    fun toParams(): Map<String, String>
        = mutableMapOf<String, String>().apply {
        this["fields"] = fields
        this["page"] = page.toString()
    }

}