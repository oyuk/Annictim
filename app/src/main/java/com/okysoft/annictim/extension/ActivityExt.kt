package com.okysoft.annictim.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

fun Context.clearStackAndStartActivity(className: Class<*>) {
    val intent = Intent(this, className)
    clearStackAndStartActivity(intent)
}

fun Context.clearStackAndStartActivity(intent: Intent) {
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}

fun Context.clearTopAndStartActivity(intent: Intent) {
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
    startActivity(intent)
}

fun Activity.openUrl(url: String) {
    val tabsIntent = CustomTabsIntent.Builder().build()
    tabsIntent.launchUrl(this, Uri.parse(url))
}