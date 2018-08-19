package com.okysoft.annictim.API.Model.Response

data class Work(
        private val id: String,
        val title: String,
        val watchersCount: Int,
        val imageUrl: String
)