package com.okysoft.annictim.presentation.setting

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okysoft.infra.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val authRepository: AuthRepository): ViewModel() {

    val openDialog = mutableStateOf(false)

    private val _openDeveloperBlog = MutableLiveData<Unit>()
    val openDeveloperBlog: LiveData<Unit> = _openDeveloperBlog

    private val _openLicense = MutableLiveData<Unit>()
    val openLicense: LiveData<Unit> = _openLicense

    val sections = listOf(
        SettingListSection("設定", listOf(
            SettingListItem("ログアウト") {
                openDialog.value = true
            }
        )),
        SettingListSection("フィードバック", listOf(
            SettingListItem("作者サイトへ") {
                _openDeveloperBlog.postValue(Unit)
            }
        )),
        SettingListSection("ライセンス", listOf(
            SettingListItem("ライセンス") {
                _openLicense.postValue(Unit)
            }
        )),
    )

    fun logout() {
        authRepository.deleteStoredAccessToken()
    }

}