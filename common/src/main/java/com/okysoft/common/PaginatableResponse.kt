package com.okysoft.common

data class PaginatableResponse<T>(
    val hasNext: Boolean,
    val nextCursor: String,
    val items: List<T>
)
