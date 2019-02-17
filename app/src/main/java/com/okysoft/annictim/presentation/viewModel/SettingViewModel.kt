package com.okysoft.annictim.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.annictim.AuthRepository
import javax.inject.Inject

class SettingViewModel constructor(private val authRepository: AuthRepository): ViewModel() {

    class Factory @Inject constructor (
            private val authRepository: AuthRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SettingViewModel(authRepository) as T
        }
    }

    fun logout() {
        authRepository.deleteStoredAccessToken()
    }

}