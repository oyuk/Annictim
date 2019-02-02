package com.okysoft.annictim

interface Diffable {

    fun isTheSame(other: Diffable): Boolean = equals(other)

    fun isContentsTheSame(other: Diffable): Boolean = equals(other)

}