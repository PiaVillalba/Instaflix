package com.piavillalba.core.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.piavillalba.core.model.CoroutineContextProvider
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren

abstract class AbstractPresenter<View : BaseView>(
    override val coroutineContextProvider: CoroutineContextProvider
) : BasePresenter<View> {

    override var view: View? = null

    override val parentJob: Job = SupervisorJob()
}

interface BasePresenter<View : BaseView> : LifecycleObserver, CoroutineScope {

    var view: View?
    val parentJob: Job
    val coroutineContextProvider: CoroutineContextProvider
    override val coroutineContext: CoroutineContext
        get() = coroutineContextProvider.mainContext + parentJob

    fun bind(view: View) {
        this.view = view
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun unBind() {
        view = null
        parentJob.apply {
            cancelChildren()
        }
    }
}
