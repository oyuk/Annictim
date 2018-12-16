package com.okysoft.annictim.presentation

enum class MeFilterStatus {
    wanna_watch, watching, watched, on_hold, stop_watching;

    fun toDisplayName(): String
        = when (this) {
        wanna_watch -> "Wanna watch"
        watching -> "Watching"
        watched -> "Watched"
        on_hold -> "On hold"
        stop_watching -> "Stop watching"
    }

    //    companion object {
//
//        private fun fromNum(num: Int): MeFilterStatus {
//            return MeFilterStatus.values().firstOrNull { it.num == num } ?: WorkTerm.Term.Winter
//        }
//
//    }
}