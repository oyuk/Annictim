package com.okysoft.annictim.domain

data class Record(
    val id: Int,
    val comment: String,
    val ratingState: String,
    val createdAt: String,
    val user: User
) {

    val rating: Int = when(ratingState) {
        "bad" -> 0
        "average" -> 1
        "good" -> 2
        "great" -> 3
        else -> 0
    }

}