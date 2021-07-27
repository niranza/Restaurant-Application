package com.niran.restaurantapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.niran.restaurantapplication.utils.ITEM_ID

class OrderActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    val handler = object : ItemAdapterFragmentsHandler {
        override fun startItemPreviewActivity(itemId: Int) {
            Intent(this@OrderActivity, ItemPreviewActivity::class.java).apply {
                putExtra(ITEM_ID, itemId)
            }.also { this@OrderActivity.startActivity(it) }
        }
    }

    interface ItemAdapterFragmentsHandler {
        fun startItemPreviewActivity(itemId: Int)
    }
}