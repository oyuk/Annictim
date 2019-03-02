package com.okysoft.annictim.extension

import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat("YYYY/MM/dd", Locale.US)
private val dateTimeFormat = SimpleDateFormat("YYYY/MM/dd HH:MM", Locale.US)

fun Date.toReadableDateString(): String {
    dateFormat.timeZone = TimeZone.getTimeZone("Asia/Tokyo")
    return dateFormat.format(this)
}

fun Date.toReadableDateTimeString(): String {
    dateTimeFormat.timeZone = TimeZone.getTimeZone("Asia/Tokyo")
    return dateTimeFormat.format(this)
}
