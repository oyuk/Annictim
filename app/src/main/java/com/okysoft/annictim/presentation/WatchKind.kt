package com.okysoft.annictim.presentation

enum class WatchKind(val rawValue: String) {
    wanna_watch("wanna_watch"),
    watching("watching"),
    watched("watched"),
    on_hold("on_hold"),
    stop_watching("stop_watching"),
    no_select("no_select");

    fun toDisplayName(): String
        = when (this) {
        wanna_watch -> "Wanna watch"
        watching -> "Watching"
        watched -> "Watched"
        on_hold -> "On hold"
        stop_watching -> "Stop watching"
        no_select -> ""
    }

    fun toJA(): String
        = when (this) {
        wanna_watch -> "見たい"
        watching -> "見てる"
        watched -> "見た"
        on_hold -> "中断"
        stop_watching -> "中止"
        no_select -> "未定"
    }

    companion object {

        const val meKindCount: Int = 5

        fun fromString(s: String): WatchKind {
            return WatchKind.values().filter { s == it.rawValue }.firstOrNull() ?: no_select
        }

        fun fromNum(num: Int): WatchKind {
            return WatchKind.values().firstOrNull { it.ordinal == num } ?: no_select
        }

        fun fromJA(ja: String): WatchKind {
            return when (ja) {
                "見たい" -> wanna_watch
                "見てる" -> watching
                "見た" -> watched
                "中断" -> on_hold
                "中止" -> stop_watching
                else -> no_select
            }
        }
    }

}
