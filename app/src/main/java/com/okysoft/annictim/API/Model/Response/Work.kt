package com.okysoft.annictim.API.Model.Response

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class Work (
        val id: String,
        val title: String,
        val watchersCount: Int,
        val imageUrl: String
): PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelWork.CREATOR
    }

}
