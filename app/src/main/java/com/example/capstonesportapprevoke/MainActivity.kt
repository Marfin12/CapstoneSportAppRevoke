package com.example.capstonesportapprevoke

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.example.capstonesportapprevoke.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(root)
//            setSupportActionBar(incMainFragment.toolbar)
//
//            val navHostFragment = supportFragmentManager
//                .findFragmentById(R.id.navHostFragment) as NavHostFragment
//            navController = navHostFragment.navController
//
//            toolbar.setTitleTextColor(resources.getColor(R.color.design_default_color_background))
//
//            NavigationUI.setupWithNavController(navigationView, navController)
//            setupActionBarWithNavController(navController, drawerLayout)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            binding.drawerLayout
        )
    }
}
