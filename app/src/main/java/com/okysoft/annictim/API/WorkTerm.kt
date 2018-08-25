package com.okysoft.annictim.API

import java.lang.Math.abs
import java.util.*

fun Calendar.nextYear(): Int {
    add(Calendar.YEAR, 1)
    return get(Calendar.YEAR)
}

fun Calendar.previousYear(): Int {
    add(Calendar.YEAR, -1)
    return get(Calendar.YEAR)
}

fun Calendar.nextMonth(): Int {
    add(Calendar.MONTH, 1)
    return get(Calendar.MONTH)
}

fun Calendar.previousMonth(): Int {
    add(Calendar.MONTH, -1)
    return get(Calendar.MONTH)
}

fun Calendar.nextTerm() {

}

enum class WorkTerm {

    Current {
        override fun term(): String {
            val calender = Calendar.getInstance()
            return workTerm(calender.get(Calendar.YEAR), Term.fromMonth(calender.get(Calendar.MONTH)))
        }
    },

    Previous {
        override fun term(): String {
            val calender = Calendar.getInstance()
            val term = Term.fromMonth(calender.get(Calendar.MONTH))
            if (term.isFirst()) {
                calender.previousYear()
            }
            term.previous()
            return workTerm(calender.get(Calendar.YEAR), term)
        }
    },

    Next {
        override fun term(): String {
            val calender = Calendar.getInstance()
            val term = Term.fromMonth(calender.get(Calendar.MONTH))
            if (term.isLast()) {
                calender.nextYear()
            }
            term.next()
            return workTerm(calender.get(Calendar.YEAR), term)
        }
    };

    abstract fun term(): String

    protected enum class Term(val num: Int) {
        Winter(0),
        Spring(1),
        Summer(2),
        Autumn(3);

        override fun toString(): String {
            return when (this) {
                Winter -> "winter"
                Spring -> "spring"
                Summer -> "summer"
                Autumn -> "autumn"
            }
        }

        fun isLast(): Boolean {
            return this == Autumn
        }

        fun isFirst(): Boolean {
            return this == Winter
        }

        fun next(): Term {
            return fromNum(values().size % (num + 1))
        }

        fun previous(): Term {
            return fromNum(values().size % abs(num - 1))
        }

        companion object {

            private fun fromNum(num: Int): Term {
                return values().firstOrNull { it.num == num } ?: Winter
            }

            fun fromMonth(month: Int): Term {
                return when (month) {
                    0, 1, 2 -> Winter
                    3, 4, 5 -> Spring
                    6, 7, 8 -> Summer
                    9, 10, 11 -> Autumn
                    else -> Winter
                }
            }
        }

    }

    protected fun workTerm(year: Int, term: Term): String {
        return "${year}-${term}"
    }

}