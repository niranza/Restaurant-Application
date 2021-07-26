package com.niran.restaurantapplication.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.niran.restaurantapplication.database.models.Item
import com.niran.restaurantapplication.databinding.ItemItemBinding

class ItemAdapter : ListAdapter<Item, ItemAdapter.ItemViewHolder>(DiffUtilCallBack) {

    class ItemViewHolder private constructor(private val binding: ItemItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.apply {
                itemNameTv.text = item.itemName
                ingredientsTv.text = "Tomato POTATO"
                priceTv.text = item.itemPrice.toString()
                itemIv.setImageResource(item.itemImageId)
            }
        }

        companion object {
            fun create(parent: ViewGroup): ItemViewHolder {
                val binding = ItemItemBinding.inflate(LayoutInflater.from(parent.context))
                return ItemViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DiffUtilCallBack : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}