package com.okysoft.annictim.presentation.person

import androidx.lifecycle.LiveData

interface PersonViewModel {

    sealed class State {
        object Loading: State()
        data class Success(val listItem: List<PersonListItem>): State()
        object Error: State()
    }

    val stateFlow: LiveData<State>
    fun fetch()
}