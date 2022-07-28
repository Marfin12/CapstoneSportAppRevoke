package com.example.capstonesportapprevoke.favorite

import android.os.Looper
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.example.capstonesportapprevoke.MainCoroutineScopeRule
import com.example.capstonesportapprevoke.core.data.SportRepository
import com.example.capstonesportapprevoke.core.data.source.local.entity.TeamEntity
import com.example.capstonesportapprevoke.core.domain.model.Team
import com.example.capstonesportapprevoke.core.domain.usecase.SportUseCase
import com.example.capstonesportapprevoke.core.utils.DataMapper
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

class FavoriteViewModelTest {

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

    @Mock
    private lateinit var mockObserver: Observer<List<Team>>

    @Mock
    private lateinit var mockFlowResourceSport: Flow<List<Team>>

    @Mock
    private lateinit var mockEntityTeam: TeamEntity

    @Mock
    private lateinit var mockTeam: Team

    @Captor
    private lateinit var captor: ArgumentCaptor<List<Team>>

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private lateinit var favoriteViewModel: FavoriteViewModel

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
    fun test_favoriteTeamAdapter_function() = runBlockingTest {
        val fakeApiService = FakeApiService()
        val resultFlow = fakeTestRepository.getFavoriteTeam()

        `when`(sportUseCase.getFavoriteTeam()).thenReturn(
            resultFlow
        )

        favoriteViewModel = FavoriteViewModel(sportUseCase)
        favoriteViewModel.favoriteTeam.observeForever(mockObserver)
        verify(mockObserver).onChanged(captor.capture())

        val expectedFavoriteTeam = fakeApiService.getTeamList().filter {
            it.isFavorite
        }

        Assert.assertEquals(expectedFavoriteTeam::class.java, captor.value::class.java)
        Assert.assertEquals(
            expectedFavoriteTeam,
            captor.value
        )
    }

    @Test
    fun test_removeFavoriteSport_function() = runBlockingTest {
        val fakeApiService = FakeApiService()
        val dummyTeam = fakeApiService.getTeamList()[0]

        favoriteViewModel = FavoriteViewModel(sportUseCase)
        favoriteViewModel.removeFavoriteSport(dummyTeam)

        verify(sportUseCase, times(1)).setFavoriteTeam(dummyTeam, false)
    }

}