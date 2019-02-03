package com.okysoft.annictim.presentation

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class CastRequestParams(
    val fields: FieldType = FieldType.Minimum,
    val ids: List<Int> = listOf(),
    val workId: Int? = null,
    val page: Int = 1,
    val perPage: Int = 20,
    val sortId: String? = null,
    val sortSortNumber: Int? = null): PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelCastRequestParams.CREATOR
    }

    enum class FieldType {
        Minimum, All;

        override fun toString(): String
            = when(this) {
            Minimum -> "id,name,name_en,sort_number"
            All -> ""
        }
    }

    fun toParams(): Map<String, String>
        = mutableMapOf<String, String>().apply {
        this["fields"] = fields.toString()
        if (ids.isNotEmpty()) {
            this["filter_ids"] = ids.toString()
        }
        workId.let {
            this["filter_work_id"] = workId.toString()
        }
        this["page"] = page.toString()
        this["per_page"] = perPage.toString()
        sortId?.let {
            this["sort_id"] = it
        }
        sortSortNumber?.let {
            this["sort_sort_number"] = it.toString()
        }
    }

}

