package com.okysoft.annictim.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.okysoft.infra.repository.AuthRepository
import javax.inject.Inject

class SettingViewModel constructor(private val authRepository: com.okysoft.infra.repository.AuthRepository): ViewModel() {


    fun logout() {
        authRepository.deleteStoredAccessToken()
    }

}