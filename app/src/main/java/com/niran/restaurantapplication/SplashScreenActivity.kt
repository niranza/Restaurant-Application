package com.niran.restaurantapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.niran.restaurantapplication.databinding.ActivitySplashScreenBinding
import com.niran.restaurantapplication.utils.LoadingHandler
import com.niran.restaurantapplication.viewmodels.SplashScreenViewModel
import com.niran.restaurantapplication.viewmodels.SplashScreenViewModelFactory

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private val viewModel: SplashScreenViewModel by viewModels {
        SplashScreenViewModelFactory((this.application as RestaurantApplication).itemRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.hide()

        /*lifecycleScope.launch {
            FirebaseFireStore.itemApiService.saveItem(
                Item(
                    itemName = "Bacon",
                    itemPrice = 30.6,
                    itemImageId = R.drawable.ic_food,
                    itemType = FoodTypes.FOOD.ordinal,
                ), this@SplashScreenActivity
            )
        }*/

        loadData()
    }

    private fun loadData() {
        viewModel.loadSplashScreen(object : LoadingHandler {
            override fun onSuccess() {
                Toast.makeText(this@SplashScreenActivity, "Success", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(error: Exception) {
                Toast.makeText(this@SplashScreenActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        startMainActivity()
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed({ startActivity(intent) }, 1000)
    }
}