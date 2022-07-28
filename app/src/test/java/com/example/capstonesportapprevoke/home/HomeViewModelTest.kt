package com.example.capstonesportapprevoke.home

import android.os.Looper
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import com.example.capstonesportapprevoke.MainCoroutineScopeRule
import com.example.capstonesportapprevoke.core.data.Resource
import com.example.capstonesportapprevoke.core.data.SportRepository
import com.example.capstonesportapprevoke.core.domain.model.Sport
import com.example.capstonesportapprevoke.core.domain.usecase.SportUseCase
import com.example.capstonesportapprevoke.data.source.FakeApiService
import com.example.capstonesportapprevoke.data.source.FakeTestRepository
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*

class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var sportUseCase: SportUseCase

    @Mock
    private lateinit var fakeTestRepository: FakeTestRepository

    @Mock
    private lateinit var sportRepository: SportRepository

    @Mock
    private lateinit var mockLooper: Looper

    @Mock
    private lateinit var mockArchTaskExecutor: ArchTaskExecutor

    @Mock
    private lateinit var mockObserver: Observer<Resource<List<Sport>>>

    @Mock
    private lateinit var mockFlowResourceSport: Flow<Resource<List<Sport>>>


    @Captor
    private lateinit var captor: ArgumentCaptor<Resource<List<Sport>>>

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        mockkStatic(Looper::class)
        mockkStatic(ArchTaskExecutor::class)

        every { Looper.getMainLooper() } returns mockLooper
        every { ArchTaskExecutor.getInstance()} returns mockArchTaskExecutor
        `when`(mockArchTaskExecutor.isMainThread).thenReturn(true)

        fakeTestRepository = FakeTestRepository()
    }

    @Test
    fun test_sportAdapter_livedata() = runBlockingTest {
        val dummyName = FakeApiService.dummyName
        val fakeApiService = FakeApiService()
        val resultFlow = fakeTestRepository.getAllSport()
        `when`(sportRepository.getAllSport()).thenReturn(resultFlow)

        val liveDataCountry = fakeTestRepository.getAllCountry()
        val liveDataTeam = fakeTestRepository.getAllTeam()
        val liveDataTeamByCountry = fakeTestRepository.getTeamFromCountry(dummyName)
        val liveDataFavoriteTeam = fakeTestRepository.getFavoriteTeam()
        val liveDataSeenTeam = fakeTestRepository.getSeenTeam()

        `when`(sportUseCase.getAllSport()).thenReturn(resultFlow)
        `when`(sportUseCase.getAllCountry()).thenReturn(liveDataCountry)
        `when`(sportUseCase.getAllTeam()).thenReturn(liveDataTeam)
        `when`(sportUseCase.getTeamFromCountry(dummyName)).thenReturn(liveDataTeamByCountry)
        `when`(sportUseCase.getFavoriteTeam()).thenReturn(liveDataFavoriteTeam)
        `when`(sportUseCase.getSeenTeam()).thenReturn(liveDataSeenTeam)

        homeViewModel = HomeViewModel(sportUseCase)
        homeViewModel.sportAdapter.observeForever(mockObserver)
        verify(mockObserver).onChanged(captor.capture())
        Assert.assertEquals(Resource.Success(resultFlow)::class.java, captor.value::class.java)

        Assert.assertEquals(
            fakeApiService.getSportList().sports[0],
            captor.value.data!![0]
        )
    }

}