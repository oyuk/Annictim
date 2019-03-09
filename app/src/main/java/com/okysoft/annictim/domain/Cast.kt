package com.okysoft.annictim.domain

import com.okysoft.annictim.infra.api.model.response.Character

data class Cast(
    val character: Character,
    val id: Int,
    val name: String
//    val nameEn: String,
//    val person: People,
//    val sortNumber: Int,
//    val work: Work
)
