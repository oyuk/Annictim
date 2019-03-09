package com.okysoft.annictim.domain

data class User(
    val avatarUrl: String,
    val id: Int,
    val name: String,
    val onHoldCount: Int,
    val recordsCount: Int,
    val stopWatchingCount: Int,
    val username: String,
    val wannaWatchCount: Int,
    val watchedCount: Int,
    val watchingCount: Int
)