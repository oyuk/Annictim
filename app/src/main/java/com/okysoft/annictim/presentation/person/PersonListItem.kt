package com.okysoft.annictim.presentation.person

import androidx.compose.runtime.Immutable

@Immutable
data class PersonListItem(val title: String, val content: String, val linkable: Boolean = false)
