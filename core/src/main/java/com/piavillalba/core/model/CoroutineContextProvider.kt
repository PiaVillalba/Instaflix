package com.piavillalba.core.model

import kotlin.coroutines.CoroutineContext

data class CoroutineContextProvider(
    val mainContext: CoroutineContext,
    val backgroundContext: CoroutineContext
)
