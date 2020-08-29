package com.okysoft.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Series(
    val id: Int?,
    val name: String?,
    val nameEn: String?,
    val nameRo: String?
): Parcelable