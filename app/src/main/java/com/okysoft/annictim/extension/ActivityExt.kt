package com.okysoft.annictim.extension

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.customtabs.CustomTabsIntent

fun Activity.clearStackAndStartActivity(className: Class<*>) {
    val intent = Intent(this, className)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    this.startActivity(intent)
}

fun Activity.openUrl(url: String) {
    val tabsIntent = CustomTabsIntent.Builder().build()
    tabsIntent.launchUrl(this, Uri.parse(url))
}