package com.niran.restaurantapplication.utils.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.niran.restaurantapplication.R
import com.niran.restaurantapplication.database.models.Ingredient
import com.niran.restaurantapplication.databinding.IngredientItemBinding

//for future use

class IngredientAdapter :
    ListAdapter<Ingredient, IngredientAdapter.IngredientViewHolder>(IngredientCallBack) {

    class IngredientViewHolder(private val binding: IngredientItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ingredient: Ingredient) {

            val context = itemView.context

            binding.apply {
                ingredientNameTv.apply {
                    text = ingredient.ingredientName
                    val originalColor = currentTextColor
                    if (ingredient.isIngredientRemoved)
                        setTextColor(ContextCompat.getColor(context, R.color.teal_200))
                    else
                        setTextColor(originalColor)
                }
            }
        }

        companion object {
            fun create(parent: ViewGroup): IngredientViewHolder {
                val binding = IngredientItemBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return IngredientViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object IngredientCallBack : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return newItem.ingredientName == oldItem.ingredientName
        }

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return newItem == oldItem
        }
    }
}