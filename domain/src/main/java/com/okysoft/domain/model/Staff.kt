package com.okysoft.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Staff(
    val id: Int,
    val name: String,
    val roleOther: String?,
    val roleText: String
): Parcelable