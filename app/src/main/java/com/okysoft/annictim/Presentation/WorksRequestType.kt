package com.okysoft.annictim.Presentation

import com.okysoft.annictim.API.WorkTerm
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

sealed class WorksRequestType: PaperParcelable {

    abstract fun toParams(): String

    @PaperParcel
    data class Term(val workTerm: WorkTerm): WorksRequestType() {
        companion object {
            @JvmField val CREATOR = PaperParcelRequestType_Term.CREATOR
        }

        override fun toParams(): String = workTerm.term()
    }

    @PaperParcel
    data class Me(val param: String): WorksRequestType() {
        companion object {
            @JvmField val CREATOR = PaperParcelRequestType_Me.CREATOR
        }

        override fun toParams(): String = param
    }

}
