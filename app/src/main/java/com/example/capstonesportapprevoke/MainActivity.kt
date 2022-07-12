package com.example.capstonesportapprevoke

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.capstonesportapprevoke.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var graph: NavGraph

    companion object {
        var isFromFavoriteFragment = false
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(root)
            setSupportActionBar(root.toolbar)

            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.navHostFragment) as NavHostFragment
            navController = navHostFragment.navController
            val inflater = navHostFragment.navController.navInflater
            graph = inflater.inflate(R.navigation.nav_graph)

            toolbar.setTitleTextColor(ContextCompat.getColor(applicationContext, com.google.android.material.R.color.design_default_color_background))

            NavigationUI.setupWithNavController(navigationView, navController)
            setupActionBarWithNavController(navController, drawerLayout)

            if (isFromFavoriteFragment) {
                popupBackStackFavorite()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            binding.drawerLayout
        )
    }

    private fun popupBackStackFavorite() {
        navController.navigate(R.id.favoriteFragment)
    }
}
