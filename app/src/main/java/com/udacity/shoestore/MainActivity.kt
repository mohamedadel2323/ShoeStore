package com.udacity.shoestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.udacity.shoestore.databinding.ActivityMainBinding
import timber.log.Timber

lateinit var shoeListViewModel: MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        shoeListViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        navController = findNavController(R.id.myNavHostFragment)
        var appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (navController.currentDestination?.id == R.id.loginFragment
            || navController.currentDestination?.id == R.id.welcomeFragment
            || navController.currentDestination?.id == R.id.instructionFragment
            || navController.currentDestination?.id == R.id.shoeListFragment) {
            finish()
            return true
        }
        return navController.navigateUp()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (navController.currentDestination?.id == R.id.loginFragment) {
            return false
        }
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)
    }
}
