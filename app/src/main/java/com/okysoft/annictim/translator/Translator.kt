package com.okysoft.annictim.translator

interface Translator<T,R> {
    fun translate(response: T): R
}