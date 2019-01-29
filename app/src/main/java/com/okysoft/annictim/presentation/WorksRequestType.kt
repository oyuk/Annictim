package com.okysoft.annictim.presentation

import com.okysoft.annictim.api.WorkTerm
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

sealed class WorksRequestType: PaperParcelable {

    abstract fun toParams(): Map<String, String>

    @PaperParcel
    data class Filter(val ids: List<Int>): WorksRequestType() {
        companion object {
            @JvmField val CREATOR = PaperParcelWorksRequestType_Filter.CREATOR
        }

        override fun toParams(): Map<String, String> = mapOf(Pair("filter_ids", ids.joinToString(separator = "")))
    }

    @PaperParcel
    data class Term(val workTerm: WorkTerm): WorksRequestType() {
        companion object {
            @JvmField val CREATOR = PaperParcelWorksRequestType_Term.CREATOR
        }

        override fun toParams(): Map<String, String> = mapOf(Pair("filter_season", workTerm.term()))
    }

    @PaperParcel
    data class MeFilterStatus(val meFilterStatus: com.okysoft.annictim.presentation.MeFilterStatus): WorksRequestType() {
        companion object {
            @JvmField val CREATOR = PaperParcelWorksRequestType_MeFilterStatus.CREATOR
        }

        override fun toParams(): Map<String, String> = mapOf(Pair("filter_status",meFilterStatus.toString()))
    }

    @PaperParcel
    data class Search(val ids: List<Int> = listOf(), val workTerm: WorkTerm? = null, val title: String = ""): WorksRequestType() {
        companion object {
            @JvmField val CREATOR = PaperParcelWorksRequestType_Search.CREATOR
        }

        override fun toParams(): Map<String, String>
            = mutableMapOf<String, String>().apply {
            if (ids.isNotEmpty()) {
                this["filter_ids"] = ids.toString()
            }
            workTerm?.let {
                this["filter_season"] = workTerm.term()
            }
            if (title.isNotEmpty()) {
                this["filter_title"] = title
            }
        }

    }

}
