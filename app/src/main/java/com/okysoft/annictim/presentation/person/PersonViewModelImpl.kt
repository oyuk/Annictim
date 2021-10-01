package com.okysoft.annictim.presentation.person

import androidx.lifecycle.*
import com.okysoft.infra.fragment.Person
import com.okysoft.infra.repository.PersonRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PersonViewModelImpl @AssistedInject constructor(private val personRepository: PersonRepository,
                                                      @Assisted val personId: Int): ViewModel(), PersonViewModel {

    @AssistedFactory
    interface Factory {
        fun create(personId: Int): PersonViewModelImpl
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

    private val _stateFlow = MutableStateFlow<PersonViewModel.State>(PersonViewModel.State.Loading)
    override val stateFlow: LiveData<PersonViewModel.State> = _stateFlow.asLiveData()

    private fun createListItem(person: Person): List<PersonListItem> {
        return listOf(
            PersonListItem("名前", person.name),
            PersonListItem("名前（かな）", person.nameKana),
            PersonListItem("ニックネーム", person.nickname),
            PersonListItem("性別", person.genderText),
            PersonListItem("誕生日", person.birthday),
            PersonListItem("出身地", person.prefecture.name),
            PersonListItem("URL", person.url, true),
            PersonListItem("Twitter", "https://twitter.com/${person.twitterUsername}", true),
            PersonListItem("Wikipedia", person.wikipediaUrl, true),
        )
    }

    override fun fetch() {
        _stateFlow.value = PersonViewModel.State.Loading
        viewModelScope.launch {
            personRepository.get(personId)
                .map { person ->
                    person?.let { createListItem(it) } ?: emptyList()
                }
                .catch { _stateFlow.value = PersonViewModel.State.Error }
                .onEach { _stateFlow.value = PersonViewModel.State.Success(it) }
                .collect()
        }
    }

}