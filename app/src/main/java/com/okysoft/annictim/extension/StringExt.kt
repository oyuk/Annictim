package com.okysoft.annictim.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private val reviewDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'")

fun String.toDate(format: SimpleDateFormat = reviewDateFormat): Date? {
    var date: Date? = null
    try {
        date = format.parse(this)
    } catch (e: ParseException) {

    }
    return date
}