package com.okysoft.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cast(
    val character: Character,
    val id: Int,
    val name: String
//    val nameEn: String,
//    val person: People,
//    val sortNumber: Int,
//    val work: Work
): Parcelable
