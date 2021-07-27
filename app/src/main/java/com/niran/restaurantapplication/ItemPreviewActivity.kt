package com.niran.restaurantapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.niran.restaurantapplication.database.models.Item
import com.niran.restaurantapplication.databinding.ActivityItemPreviewBinding
import com.niran.restaurantapplication.utils.AppUtils
import com.niran.restaurantapplication.utils.ITEM_ID
import com.niran.restaurantapplication.utils.MAX_NUMBER_OF_ITEMS_PER_MEAL
import com.niran.restaurantapplication.viewmodels.ItemPreviewViewModel
import com.niran.restaurantapplication.viewmodels.ItemPreviewViewModelFactory

class ItemPreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemPreviewBinding

    private val viewModel: ItemPreviewViewModel by viewModels {
        ItemPreviewViewModelFactory((application as RestaurantApplication).itemRepository)
    }

    private lateinit var item: Item

    private var quantity: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemPreviewBinding.inflate(layoutInflater)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val itemId = intent?.extras?.getInt(ITEM_ID) ?: -1

        viewModel.getItem(itemId).observe(this) {
            it?.let { item ->
                binding.apply {
                    this@ItemPreviewActivity.item = item

                    quantity = item.itemQuantity

                    itemIv.setImageResource(item.itemImageId)
                    itemNameTv.text = item.itemName
                    ingredientsTv.text =
                        AppUtils.formatIngredients(item.itemIngredients.ingredientList)
                    itemPriceTv.text = item.itemPrice.toString()
                    itemQuantityTv.text = item.itemQuantity.toString()
                }
            }
        }

        binding.apply {
            addQuantityBtn.setOnClickListener {
                if (quantity < MAX_NUMBER_OF_ITEMS_PER_MEAL) quantity++
                viewModel.updateItem(getItem())
            }

            removeQuantityBtn.setOnClickListener {
                if (quantity > 0) quantity--
                viewModel.updateItem(getItem())
            }

            addBtn.setOnClickListener {
                if (item.isItemOrdered && quantity == 0) {
                    viewModel.deleteItem(getItem())
                } else if (!item.isItemOrdered && quantity > 0) {
                    viewModel.insertItem(getItem(0, true))
                    quantity = 0
                    viewModel.updateItem(getItem())
                }
                this@ItemPreviewActivity.finish()
            }
        }

        setContentView(binding.root)
    }

    private fun getItem(
        itemId: Int = item.itemId,
        isItemOrdered: Boolean = item.isItemOrdered
    ): Item = item.copy(
        itemId = itemId,
        itemQuantity = quantity,
        isItemOrdered = isItemOrdered
    )
}