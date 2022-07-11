package com.example.capstonesportapprevoke.core.utils

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.capstonesportapprevoke.core.domain.model.Team
import com.example.capstonesportapprevoke.favorite.FavoriteFragment
import com.example.capstonesportapprevoke.favorite.FavoriteFragmentDirections
import com.example.capstonesportapprevoke.home.HomeFragment
import com.example.capstonesportapprevoke.home.HomeFragmentDirections

object FragmentUtils {
    fun goToDetailScreen(teamData: Team, favoriteFragment: FavoriteFragment) {
        val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(teamData)
        findNavController(favoriteFragment).navigate(action)
    }

    fun goToDetailScreen(teamData: Team, homeFragment: HomeFragment) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailActivity(teamData)
        findNavController(homeFragment).navigate(action)
    }
}