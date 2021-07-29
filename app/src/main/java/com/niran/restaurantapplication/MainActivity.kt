package com.niran.restaurantapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.niran.restaurantapplication.utils.LoadingHandler
import com.niran.restaurantapplication.utils.NotificationsUtil
import com.niran.restaurantapplication.viewmodels.MainViewModel
import com.niran.restaurantapplication.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            (this.application as RestaurantApplication).itemRepository,
            (this.application as RestaurantApplication).dataVersionRepository,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        loadData()

    }

    private fun loadData() {
        viewModel.loadSplashScreen(object : LoadingHandler {
            override fun onSuccess() {
                Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                startMainActivity()
            }

            override fun onFailure(error: Exception) {
                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun startMainActivity() {
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

        NotificationsUtil.createNotificationChannel(
            R.string.menu_channel_id,
            R.string.menu_channel_name,
            this
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}