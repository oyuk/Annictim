package com.okysoft.domain.translator

interface Translator<T,R> {
    fun translate(response: T): R
}