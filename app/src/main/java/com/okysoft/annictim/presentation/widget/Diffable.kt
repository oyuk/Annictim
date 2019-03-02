package com.okysoft.annictim.presentation.widget

interface Diffable {

    fun isTheSame(other: Diffable): Boolean = equals(other)

    fun isContentsTheSame(other: Diffable): Boolean = equals(other)

}