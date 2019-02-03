package com.okysoft.annictim.extension

import android.net.Uri
import android.support.customtabs.CustomTabsIntent
import android.support.v4.app.Fragment

fun Fragment.openUrl(url: String) {
    val tabsIntent = CustomTabsIntent.Builder().build()
    tabsIntent.launchUrl(activity, Uri.parse(url))
}