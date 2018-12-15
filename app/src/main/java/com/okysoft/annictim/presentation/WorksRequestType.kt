package com.okysoft.annictim.presentation

import com.okysoft.annictim.api.WorkTerm
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

sealed class WorksRequestType: PaperParcelable {

    abstract fun toParams(): String

    @PaperParcel
    data class Filter(val ids: List<Int>): WorksRequestType() {
        companion object {
            @JvmField val CREATOR = PaperParcelWorksRequestType_Filter.CREATOR
        }

        override fun toParams(): String = ids.joinToString(separator = "")
    }

    @PaperParcel
    data class Term(val workTerm: WorkTerm): WorksRequestType() {
        companion object {
            @JvmField val CREATOR = PaperParcelWorksRequestType_Term.CREATOR
        }

        override fun toParams(): String = workTerm.term()
    }

    @PaperParcel
    data class MeFilterStatus(val meFilterStatus: com.okysoft.annictim.presentation.MeFilterStatus): WorksRequestType() {
        companion object {
            @JvmField val CREATOR = PaperParcelWorksRequestType_MeFilterStatus.CREATOR
        }

        override fun toParams(): String = meFilterStatus.toString()
    }

}
