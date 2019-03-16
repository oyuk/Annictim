package com.okysoft.annictim.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class CoroutineScopeViewModel(private val context: CoroutineContext): ViewModel(), CoroutineScope {

    protected val job = Job()

    override val coroutineContext: CoroutineContext
        get() = context + job

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

}
