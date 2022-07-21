package com.example.capstonesportapprevoke.utilityTest

import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.example.capstonesportapprevoke.core.domain.model.Team
import com.example.capstonesportapprevoke.core.utils.FragmentUtils.goToDetailScreen
import com.example.capstonesportapprevoke.core.utils.FragmentUtils.handleBackStackNavigation
import com.example.capstonesportapprevoke.core.utils.FragmentUtils.isFromFavoriteFragment
import com.example.capstonesportapprevoke.favorite.FavoriteFragment
import com.example.capstonesportapprevoke.home.HomeFragment
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*

class FragmentUtilsTest {

    @Mock
    private lateinit var team: Team

    @Mock
    private lateinit var favoriteFragment: FavoriteFragment

    @Mock
    private lateinit var homeFragment: HomeFragment

    @Mock
    private lateinit var parentFragment: NavHostFragment

    @Mock
    private lateinit var navController: NavController

    @Mock
    private lateinit var navDirections: NavDirections

    @Mock
    private lateinit var mockedUnit: Unit

    @Mock
    private lateinit var mockedView: View

    @Mock
    private lateinit var fragmentManager: FragmentManager

    @Before
    fun setup() {
        team = mock(Team::class.java)

        favoriteFragment = mock(FavoriteFragment::class.java)
        homeFragment = mock(HomeFragment::class.java)

        parentFragment = mock(NavHostFragment::class.java)
        fragmentManager = mock(FragmentManager::class.java)

        navController = mock(NavController::class.java)
        navDirections = mock(NavDirections::class.java)

        mockedUnit = mock(Unit::class.java)
        mockedView = mock(View::class.java)
    }

    @Test
    fun test_goToDetailScreen_from_fragment_favorite_function() {
        `when`(favoriteFragment.requireView()).thenReturn(mockedView)
        `when`(favoriteFragment.parentFragmentManager).thenReturn(fragmentManager)
        `when`(fragmentManager.primaryNavigationFragment).thenReturn(parentFragment)
        `when`(NavHostFragment.findNavController(favoriteFragment))
            .thenReturn(navController)
        `when`(NavHostFragment.findNavController(favoriteFragment))
            .thenReturn(navController)

        goToDetailScreen(team, favoriteFragment)

        Assert.assertEquals(isFromFavoriteFragment, true)
        verify(navController, times(1)).navigate(any(NavDirections::class.java))
    }

    @Test
    fun test_goToDetailScreen_from_fragment_home_function() {
        `when`(homeFragment.requireView()).thenReturn(mockedView)
        `when`(homeFragment.parentFragmentManager).thenReturn(fragmentManager)
        `when`(fragmentManager.primaryNavigationFragment).thenReturn(parentFragment)
        `when`(NavHostFragment.findNavController(homeFragment))
            .thenReturn(navController)
        `when`(NavHostFragment.findNavController(homeFragment))
            .thenReturn(navController)

        goToDetailScreen(team, homeFragment)

        Assert.assertEquals(isFromFavoriteFragment, false)
        verify(navController, times(1)).navigate(any(NavDirections::class.java))
    }

    @Test
    fun test_handleBackStackNavigation_from_fragment_favorite_function() {
        handleBackStackNavigation(navController)
        verify(navController, never()).navigate(anyInt())
    }

    @Test
    fun test_handleBackStackNavigation_from_fragment_home_function() {
        isFromFavoriteFragment = true
        handleBackStackNavigation(navController)
        verify(navController, times(1)).navigate(anyInt())
    }
}