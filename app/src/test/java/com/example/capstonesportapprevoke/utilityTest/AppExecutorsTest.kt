package com.example.capstonesportapprevoke.utilityTest

import com.example.capstonesportapprevoke.core.utils.AppExecutors
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import java.util.concurrent.Executor

class AppExecutorsTest {

    @Mock
    private lateinit var appExecutors: AppExecutors

    @Mock
    private lateinit var executors: Executor

    @Mock
    private lateinit var expectedExecutor: Unit

    @Before
    fun setup() {
        appExecutors = mock(AppExecutors::class.java)
        executors = mock(Executor::class.java)
        expectedExecutor = mock(Unit::class.java)
    }

    @Test
    fun test_diskIO_execute() {
        `when`(appExecutors.diskIO()).thenReturn(executors)
        val mockedExecutor = appExecutors.diskIO().execute {}
        Assert.assertEquals(expectedExecutor, mockedExecutor)
    }
}