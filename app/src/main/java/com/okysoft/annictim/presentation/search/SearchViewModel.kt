package com.okysoft.annictim.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.okysoft.data.WorkRequestParams

class SearchViewModel: ViewModel() {
    companion object {

        private val REGEX: Regex = """(\d{4})""".toRegex()
    }

    private val _transitionTo = MutableLiveData<WorkRequestParams>()
    val transitionTo: LiveData<WorkRequestParams> = _transitionTo

    fun search(searchCondition: SearchCondition) {
        val year = REGEX.find(searchCondition.year ?: "")?.value ?: ""
        val season = when (searchCondition.season) {
            "全体" -> "all"
            "春" -> "spring"
            "夏" -> "summer"
            "秋" -> "autumn"
            "冬" -> "winter"
            else -> ""
        }
        val seasonString = if (year.isNotBlank() && season.isNotBlank()) {
            "$year-$season"
        } else {
            null
        }
        val requestParams = WorkRequestParams(title = searchCondition.title, season = seasonString)
        _transitionTo.postValue(requestParams)
    }
}