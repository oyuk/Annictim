package com.okysoft.annictim.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.okysoft.annictim.infra.api.model.request.WorkRequestParams
import com.okysoft.annictim.extension.toLiveData
import io.reactivex.Flowable
import io.reactivex.functions.Function3
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.withLatestFrom

class SearchViewModel: ViewModel() {

    private val titleProcessor = BehaviorProcessor.createDefault("")
    private val yearProcessor = BehaviorProcessor.createDefault("")
    private val seasonProcessor = BehaviorProcessor.createDefault("")
    private val tappedSearch = PublishProcessor.create<Unit>()

    companion object {
        private val REGEX: Regex = """(\d{4})""".toRegex()
    }

    val transitionTo: LiveData<WorkRequestParams>
    val enableSeasonSelect: LiveData<Boolean>

    init {
        enableSeasonSelect = yearProcessor
            .map { it != "全体" }
            .toLiveData()

        val yearParams = yearProcessor
            .map { REGEX.find(it)?.value ?: "" }

        val seasonParams = seasonProcessor
            .map {
                when (it) {
                    "全体" -> "all"
                    "春" -> "spring"
                    "夏" -> "summer"
                    "秋" -> "autumn"
                    "冬" -> "winter"
                    else -> ""
                }
            }

        val parameter =
            Flowable.combineLatest(titleProcessor, yearParams, seasonParams, Function3 {
                title: String, year: String, season: String ->
                val seasonString = if (year.isNotBlank() && season.isNotBlank()) {
                    "$year-$season"
                } else {
                    null
                }
                WorkRequestParams(title = title, season = seasonString)
            })

        transitionTo = tappedSearch.withLatestFrom(parameter)
            .map { it.second }
            .toLiveData()
    }

    fun setTitle(title: CharSequence) {
        titleProcessor.onNext(title.toString())
    }

    fun selectYear(year: String) {
        yearProcessor.onNext(year)
    }

    fun selectSeason(season: String) {
        seasonProcessor.onNext(season)
    }

    fun tappedSearch() {
        tappedSearch.onNext(Unit)
    }

}