package com.example.capstonesportapprevoke.detail

import android.os.Looper
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.example.capstonesportapprevoke.MainCoroutineScopeRule
import com.example.capstonesportapprevoke.core.data.source.local.entity.TeamEntity
import com.example.capstonesportapprevoke.core.domain.model.Team
import com.example.capstonesportapprevoke.core.domain.usecase.SportUseCase
import com.example.capstonesportapprevoke.data.source.FakeApiService
import com.example.capstonesportapprevoke.data.source.FakeTestRepository
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*

class DetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Spy
    private lateinit var sportUseCase: SportUseCase

    @Mock
    private lateinit var fakeTestRepository: FakeTestRepository

    @Mock
    private lateinit var mockLooper: Looper

    @Mock
    private lateinit var mockArchTaskExecutor: ArchTaskExecutor

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        mockkStatic(Looper::class)
        mockkStatic(ArchTaskExecutor::class)

        every { Looper.getMainLooper() } returns mockLooper
        every { ArchTaskExecutor.getInstance()} returns mockArchTaskExecutor
        `when`(mockArchTaskExecutor.isMainThread).thenReturn(true)

        fakeTestRepository = FakeTestRepository()
        val resultFlow = fakeTestRepository.getFavoriteTeam()

        `when`(sportUseCase.getFavoriteTeam()).thenReturn(
            resultFlow
        )
    }

    @Test
    fun test_setSeenTeam_function() = runBlockingTest {
        val fakeApiService = FakeApiService()
        val dummyTeam = fakeApiService.getTeamList()[0]

        detailViewModel = DetailViewModel(sportUseCase)
        detailViewModel.setSeenTeam(dummyTeam)

        verify(sportUseCase, times(1)).setSeenTeam(dummyTeam, true)
    }

    @Test
    fun test_setFavoriteSport_function() = runBlockingTest {
        val fakeApiService = FakeApiService()
        val dummyTeam = fakeApiService.getTeamList()[0]

        detailViewModel = DetailViewModel(sportUseCase)
        detailViewModel.setFavoriteSport(dummyTeam, true)

        verify(sportUseCase, times(1)).setFavoriteTeam(dummyTeam, true)
    }

}