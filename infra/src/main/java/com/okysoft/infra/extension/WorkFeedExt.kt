package com.okysoft.infra.extension

import com.okysoft.infra.fragment.WorkFeed

fun WorkFeed.seasonText(): String? {
    if (seasonYear == null || seasonName == null) {
        return null
    }
    return "${seasonYear}年${seasonName.toJA() ?: ""}"
}
