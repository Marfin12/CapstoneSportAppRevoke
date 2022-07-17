package com.example.capstonesportapprevoke.utils

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.capstonesportapprevoke.R
import com.example.capstonesportapprevoke.core.domain.model.Team
import com.example.capstonesportapprevoke.favorite.FavoriteFragment
import com.example.capstonesportapprevoke.favorite.FavoriteFragmentDirections
import com.example.capstonesportapprevoke.home.HomeFragment
import com.example.capstonesportapprevoke.home.HomeFragmentDirections

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

    fun handleBackStackNavigation(navController: NavController) {
        if (isFromFavoriteFragment) navController.navigate(R.id.favoriteFragment)
    }
}