package com.okysoft.annictim.api.model

import com.okysoft.annictim.presentation.WorksRequestType
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class WorksRequestParamModel (
        val worksRequestType: WorksRequestType,
        val fields: Fields
): PaperParcelable {

    enum class Fields {
        All,
        Feed;

        fun toParams(): String {
            return when (this) {
                All -> ""
                Feed -> "id,title,images"
            }
        }
    }

    companion object {
        @JvmField val CREATOR = PaperParcelWorksRequestParamModel.CREATOR
    }

}