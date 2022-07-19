package com.example.capstonesportapprevoke

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.capstonesportapprevoke.core.utils.FragmentUtils.goToNewFeatureFragment
import com.example.capstonesportapprevoke.core.utils.FragmentUtils.handleBackStackNavigation
import com.example.capstonesportapprevoke.databinding.ActivityMainBinding
import com.example.capstonesportapprevoke.home.HomeFragment
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
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
                val homeFragment = HomeFragment()
                when (it.itemId) {
                    R.id.newFeatureFragment -> {
                        try {
                            goToNewFeatureFragment(drawerLayout, navController, it)
                            true
                        } catch (ex: Exception) {
                            installNewFeatureModule(drawerLayout, navController, it)
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

    private fun installNewFeatureModule(
        drawerLayout: DrawerLayout, navController: NavController, menuItem: MenuItem
    ): Boolean {
        val splitInstallManager = SplitInstallManagerFactory.create(this)
        val moduleChat = "newFeature"
        if (splitInstallManager.installedModules.contains(moduleChat)) {
            Toast.makeText(this, "Open module", Toast.LENGTH_SHORT).show()
            goToNewFeatureFragment(drawerLayout, navController, menuItem)
            return true
        } else {
            val request = SplitInstallRequest.newBuilder()
                .addModule(moduleChat)
                .build()
            splitInstallManager.startInstall(request)
                .addOnSuccessListener {
                    Toast.makeText(this, "Success installing module", Toast.LENGTH_SHORT).show()
                    goToNewFeatureFragment(drawerLayout, navController, menuItem)
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error installing module", Toast.LENGTH_SHORT).show()
                }
            return false
        }
    }
}
