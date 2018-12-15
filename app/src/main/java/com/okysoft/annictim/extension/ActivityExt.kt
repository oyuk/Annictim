package com.okysoft.annictim.extension

import android.app.Activity
import android.content.Intent

fun Activity.clearStackAndStartActivity(className: Class<*>) {
    val intent = Intent(this, className)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    this.startActivity(intent)
}