package com.okysoft.annictim.extension

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment

fun Fragment.openUrl(url: String) {
    val tabsIntent = CustomTabsIntent.Builder().build()
    tabsIntent.launchUrl(activity, Uri.parse(url))
}