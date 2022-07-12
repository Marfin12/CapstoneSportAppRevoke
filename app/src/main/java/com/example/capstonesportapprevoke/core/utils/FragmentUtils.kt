package com.example.capstonesportapprevoke.core.utils

import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.capstonesportapprevoke.MainActivity
import com.example.capstonesportapprevoke.core.domain.model.Team
import com.example.capstonesportapprevoke.favorite.FavoriteFragment
import com.example.capstonesportapprevoke.favorite.FavoriteFragmentDirections
import com.example.capstonesportapprevoke.home.HomeFragment
import com.example.capstonesportapprevoke.home.HomeFragmentDirections

object FragmentUtils {
    fun goToDetailScreen(teamData: Team, favoriteFragment: FavoriteFragment) {
        MainActivity.isFromFavoriteFragment = true
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(teamData)
        findNavController(favoriteFragment).navigate(action)
    }

    fun goToDetailScreen(teamData: Team, homeFragment: HomeFragment) {
        MainActivity.isFromFavoriteFragment = false
        val action = HomeFragmentDirections.actionHomeFragmentToDetailActivity(teamData)
        findNavController(homeFragment).navigate(action)
    }
}