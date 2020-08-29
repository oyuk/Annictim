package com.okysoft.infra.extension

import com.okysoft.infra.type.SeasonName

fun SeasonName.toJA(): String {
    return when (this) {
        SeasonName.SPRING -> "春"
        SeasonName.SUMMER -> "夏"
        SeasonName.AUTUMN -> "秋"
        SeasonName.WINTER -> "冬"
        else -> ""
    }
}
