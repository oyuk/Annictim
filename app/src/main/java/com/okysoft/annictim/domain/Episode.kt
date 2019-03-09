package com.okysoft.annictim.domain

data class Episode(
     val id: Int,
     val numberText: String,
     val title: String
 ) {

    val fullTitle: String
        get() = "$numberText $title"
}