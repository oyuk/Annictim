package com.okysoft.annictim.presentation.person

import androidx.lifecycle.*
import com.okysoft.infra.fragment.Person
import com.okysoft.infra.repository.PersonRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PersonViewModel @AssistedInject constructor(private val personRepository: PersonRepository,
                                                  @Assisted val personId: Int): ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(personId: Int): PersonViewModel
    }

    companion object {
        fun provideFactory(
            factory: Factory,
            personId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return factory.create(personId) as T
            }
        }
    }

    private val _stateFlow = MutableStateFlow<Person?>(null)
    val stateFlow: LiveData<Person?> = _stateFlow.asLiveData()

    fun fetch() {
        viewModelScope.launch {
            personRepository.get(personId).collect {
                _stateFlow.value = it
            }
        }
    }
}