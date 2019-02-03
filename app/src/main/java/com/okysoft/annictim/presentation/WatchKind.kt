package com.okysoft.annictim.presentation

enum class WatchKind {
    wanna_watch, watching, watched, on_hold, stop_watching, no_select;

    fun toDisplayName(): String
        = when (this) {
        wanna_watch -> "Wanna watch"
        watching -> "Watching"
        watched -> "Watched"
        on_hold -> "On hold"
        stop_watching -> "Stop watching"
        no_select -> ""
    }

    companion object {

        const val meKindCount: Int = 5

        fun fromNum(num: Int): WatchKind {
            return WatchKind.values().firstOrNull { it.ordinal == num } ?: wanna_watch
        }

    }
}