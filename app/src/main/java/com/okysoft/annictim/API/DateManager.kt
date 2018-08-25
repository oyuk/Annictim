package com.okysoft.annictim.API

import java.util.*

class DateManager {

    companion object {

        fun nowDate(): String {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val monthString = when (month) {
                0, 1, 2 -> "winter"
                3, 4, 5 -> "spring"
                6, 7, 8 -> "summer"
                9, 10, 11 -> "autumn"
                else -> ""
            }
            return "${year}-${monthString}"
        }

        fun nextDate(): String {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH) + 1
            if (month == 12) {
                calendar.add(Calendar.YEAR, 1)
                val y = calendar.get(Calendar.YEAR)
                val m = 0
            }
            val monthString = when (month) {
                0, 1, 2 -> "winter"
                3, 4, 5 -> "spring"
                6, 7, 8 -> "summer"
                9, 10, 11 -> "autumn"
                else -> ""
            }
            return "${year}-${monthString}"
        }

    }

}