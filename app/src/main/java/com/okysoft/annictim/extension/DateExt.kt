package com.okysoft.annictim.extension

import java.text.SimpleDateFormat
import java.util.*

private val dateFormat = SimpleDateFormat("YYYY/MM/dd", Locale.US)

fun Date.toReadableDateTimeString() = toReadableDateString()

private fun Date.toReadableDateString(): String {
    dateFormat.timeZone = TimeZone.getTimeZone("Asia/Tokyo")
    return dateFormat.format(this)
}
