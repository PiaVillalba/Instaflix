package com.piavillalba.test

import com.piavillalba.core.model.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class CoroutineTestRule : TestRule {

    private val job = SupervisorJob()
    val testDispatcher = TestCoroutineDispatcher()
    val coroutineContextProvider = CoroutineContextProvider(testDispatcher, testDispatcher)

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                Dispatchers.setMain(testDispatcher)
                base.evaluate()
                job.cancel()
                Dispatchers.resetMain()
                job.cancel()
            }
        }
    }
}