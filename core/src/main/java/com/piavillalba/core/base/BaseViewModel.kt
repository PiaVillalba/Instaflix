package com.piavillalba.core.base

import androidx.lifecycle.ViewModel
import com.piavillalba.core.model.CoroutineContextProvider
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(val coroutineContextProvider: CoroutineContextProvider) : ViewModel(),
    CoroutineScope {

    private val job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = coroutineContextProvider.mainContext + job

    override fun onCleared() {
        job.apply {
            cancelChildren()
            cancel()
        }
        super.onCleared()
    }

}