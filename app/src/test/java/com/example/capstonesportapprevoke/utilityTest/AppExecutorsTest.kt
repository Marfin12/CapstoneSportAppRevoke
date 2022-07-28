package com.example.capstonesportapprevoke.utilityTest

import com.example.capstonesportapprevoke.core.data.source.local.LocalDataSource
import com.example.capstonesportapprevoke.core.data.source.local.entity.TeamEntity
import com.example.capstonesportapprevoke.core.data.source.local.room.SportDao
import com.example.capstonesportapprevoke.core.utils.AppExecutors
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import java.util.concurrent.Executor

class AppExecutorsTest {

    @Mock
    private lateinit var appExecutors: AppExecutors

    @Spy
    private lateinit var spyAppExecutors: AppExecutors

    @Spy
    private lateinit var spyLocalDataSource: LocalDataSource

    @Mock
    private lateinit var sportDao: SportDao

    @Mock
    private lateinit var teamEntity: TeamEntity

    @Mock
    private lateinit var executors: Executor

    @Mock
    private lateinit var expectedExecutor: Unit

    @Before
    fun setup() {
        sportDao = mock(SportDao::class.java)
        val realAppExecutors = AppExecutors()
        val localDataSource = LocalDataSource(sportDao)
        spyAppExecutors = spy(realAppExecutors)
        spyLocalDataSource = spy(localDataSource)

        appExecutors = mock(AppExecutors::class.java)
        executors = mock(Executor::class.java)
        expectedExecutor = mock(Unit::class.java)
        teamEntity = mock(TeamEntity::class.java)
    }

    @Test
    fun test_diskIO_execute() = runBlockingTest {

        spyAppExecutors.diskIO().execute {
            spyLocalDataSource.setFavoriteTeam(
                teamEntity, true
            )
        }

        delay(1000)
        verify(spyLocalDataSource, times(1)).setFavoriteTeam(
            teamEntity, true
        )
        val localDataSource = LocalDataSource(sportDao)
        localDataSource.setFavoriteTeam(teamEntity, true)
        Assert.assertEquals(teamEntity.isFavorite, true)
//        Assert.assertEquals(teamEntity.isFavorite, true)
//        val mockedExecutor = appExecutors.diskIO().execute {}
//        Assert.assertEquals(expectedExecutor, mockedExecutor)
    }
}