package com.example.capstonesportapprevoke

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.capstonesportapprevoke.core.utils.FragmentUtils
import com.example.capstonesportapprevoke.core.utils.FragmentUtils.handleBackStackNavigation
import com.example.capstonesportapprevoke.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

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

            toolbar.setTitleTextColor(ContextCompat.getColor(applicationContext, com.google.android.material.R.color.design_default_color_background))

            NavigationUI.setupWithNavController(navigationView, navController)

            handleBackStackNavigation(navController)
            setupActionBarWithNavController(navController, drawerLayout)
            navigationView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.newFeatureFragment -> {
                        try {
                            NavigationUI.onNavDestinationSelected(it, navController)
                            drawerLayout.closeDrawer(GravityCompat.START)
                            true
                        } catch (ex: Exception) {
                            Toast.makeText(
                                applicationContext,"Module not installed yet", Toast.LENGTH_SHORT
                            ).show()
                            false
                        }
                    }
                    else -> {
                        NavigationUI.onNavDestinationSelected(it, navController)
                        drawerLayout.closeDrawer(GravityCompat.START)
                        true
                    }
                }
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            binding.drawerLayout
        )
    }
}
