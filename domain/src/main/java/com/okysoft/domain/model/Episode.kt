package com.okysoft.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Episode(
     val id: Int,
     val numberText: String,
     val title: String
 ): Parcelable {

    val fullTitle: String
        get() = "$numberText $title"
}
