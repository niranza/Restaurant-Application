package com.niran.restaurantapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.niran.restaurantapplication.utils.ITEM_ID
import com.niran.restaurantapplication.utils.MENU_NOTIFICATION_ID
import com.niran.restaurantapplication.utils.NotificationsUtil

class OrderActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        NotificationsUtil.cancelNotification(this, MENU_NOTIFICATION_ID)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun startItemPreviewActivity(itemId: Int) =
        Intent(this, ItemPreviewActivity::class.java).apply {
            putExtra(ITEM_ID, itemId)
        }.also { startActivity(it) }
}