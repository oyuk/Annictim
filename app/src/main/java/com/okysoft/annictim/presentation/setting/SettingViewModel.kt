package com.okysoft.annictim.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.infra.repository.AuthRepository
import javax.inject.Inject

class SettingViewModel constructor(private val authRepository: com.okysoft.infra.repository.AuthRepository): ViewModel() {

    class Factory @Inject constructor (
            private val authRepository: com.okysoft.infra.repository.AuthRepository
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