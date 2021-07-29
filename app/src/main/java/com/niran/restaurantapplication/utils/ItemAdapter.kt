package com.niran.restaurantapplication.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.niran.restaurantapplication.database.models.Item
import com.niran.restaurantapplication.databinding.ItemItemBinding

class ItemAdapter(private val itemClickHandler: ItemClickHandler) :
    ListAdapter<Item, ItemAdapter.ItemViewHolder>(ItemCallBack) {

    class ItemViewHolder private constructor(
        private val binding: ItemItemBinding,
        private val itemClickHandler: ItemClickHandler
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.apply {
                itemNameTv.text = item.itemName
                ingredientsTv.text = AppUtils.formatIngredients(item.itemIngredients.ingredientList)
                itemPriceTv.text = item.itemPrice.toString()
                itemIv.setImageResource(item.itemImageId)
                itemView.setOnClickListener { itemClickHandler.onItemClicked(item) }

                if (item.isItemOrdered) itemQuantityTv.text = item.itemQuantity.toString()
            }
        }

        companion object {
            fun create(parent: ViewGroup, itemClickHandler: ItemClickHandler): ItemViewHolder {
                val binding = ItemItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return ItemViewHolder(binding, itemClickHandler)
            }
        }
    }

    interface ItemClickHandler {
        fun onItemClicked(item: Item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        return ItemViewHolder.create(parent, itemClickHandler)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object ItemCallBack : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return newItem.itemId == oldItem.itemId
        }

        override fun areContentsTheSame(
            oldItem: Item,
            newItem: Item
        ): Boolean {
            return newItem == oldItem
        }
    }
}