package com.niran.restaurantapplication

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.niran.restaurantapplication.database.models.Item
import com.niran.restaurantapplication.databinding.ActivityItemPreviewBinding
import com.niran.restaurantapplication.utils.FormatUtils
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
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val itemId = intent?.extras?.getInt(ITEM_ID) ?: -1

        var init = true
        viewModel.getItem(itemId).observe(this) { nullableItem ->
            nullableItem?.let { item ->
                binding.apply {
                    if (init) {
                        this@ItemPreviewActivity.item = item

                        itemIv.setImageResource(item.itemImageId)
                        itemNameTv.text = item.itemName
                        itemPriceTv.text = item.itemPrice.toString()
                        ingredientsTv.text = FormatUtils.formatIngredients(item)
                        addBtn.text = if (item.isItemOrdered) getString(R.string.save)
                        else getString(R.string.add)
                    }

                    item.itemQuantity.also { itemQuantity ->
                        quantity = itemQuantity
                        itemQuantityTv.text = itemQuantity.toString()
                    }

                    init = false
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
                    showLoadingDialog()
                    viewModel.deleteItem(getItem())
                } else if (!item.isItemOrdered && quantity > 0) {
                    showLoadingDialog()
                    viewModel.insertItem(getItem(0, true))
                    quantity = 0
                    viewModel.updateItem(getItem())
                }
                this@ItemPreviewActivity.finish()
            }
        }
    }

    private fun getItem(
        itemId: Int = item.itemId,
        isItemOrdered: Boolean = item.isItemOrdered
    ): Item = item.copy(
        itemId = itemId,
        itemQuantity = quantity,
        isItemOrdered = isItemOrdered
    )

    override fun onCreateOptionsMenu(menu: Menu?): Boolean =
        if (item.isItemOrdered) {
            menuInflater.inflate(R.menu.item_preview_menu, menu)
            true
        } else {
            menuInflater.inflate(R.menu.empty_menu, menu)
            true
        }


    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.delete_item -> {
                deleteItem()
                true
            }
            android.R.id.home -> {
                finish()
                true
            }
            else -> true
        }


    private fun deleteItem() {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.remove_item_alert_title)
            setPositiveButton(R.string.remove) { _, _ ->
                showLoadingDialog()
                viewModel.deleteItem(item)
                finish()
            }
            setNegativeButton(R.string.back) { _, _ -> }
            show()
        }
    }

    private fun showLoadingDialog() = binding.loadingLayout.apply { visibility = View.VISIBLE }

}