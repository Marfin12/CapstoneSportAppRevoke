package com.example.capstonesportapprevoke.core.utils

import android.content.Context
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.capstonesportapprevoke.R
import com.example.capstonesportapprevoke.core.domain.model.Team
import com.example.capstonesportapprevoke.favorite.FavoriteFragment
import com.example.capstonesportapprevoke.favorite.FavoriteFragmentDirections
import com.example.capstonesportapprevoke.home.HomeFragment
import com.example.capstonesportapprevoke.home.HomeFragmentDirections
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import java.lang.Exception

object FragmentUtils {

    var isFromFavoriteFragment = false

    fun goToDetailScreen(teamData: Team, favoriteFragment: FavoriteFragment) {
        isFromFavoriteFragment = true
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(teamData)
        findNavController(favoriteFragment).navigate(action)
    }

    fun goToDetailScreen(teamData: Team, homeFragment: HomeFragment) {
        isFromFavoriteFragment = false
        val action = HomeFragmentDirections.actionHomeFragmentToDetailActivity(teamData)
        findNavController(homeFragment).navigate(action)
    }

    fun goToNewFeatureFragment(
        drawerLayout: DrawerLayout, navController: NavController, menuItem: MenuItem
    ): Boolean {
        NavigationUI.onNavDestinationSelected(menuItem, navController)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun handleBackStackNavigation(navController: NavController) {
        if (isFromFavoriteFragment) navController.navigate(R.id.favoriteFragment)
    }
}