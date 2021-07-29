package com.niran.restaurantapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.niran.restaurantapplication.OrderActivity
import com.niran.restaurantapplication.RestaurantApplication
import com.niran.restaurantapplication.database.models.Item
import com.niran.restaurantapplication.databinding.FragmentFoodBinding
import com.niran.restaurantapplication.utils.adapters.ItemAdapter
import com.niran.restaurantapplication.viewmodels.FoodViewModel
import com.niran.restaurantapplication.viewmodels.FoodViewModelFactory

class FoodFragment : Fragment() {

    private lateinit var binding: FragmentFoodBinding

    private val viewModel: FoodViewModel by viewModels {
        FoodViewModelFactory((activity?.application as RestaurantApplication).itemRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFoodBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemAdapter(object : ItemAdapter.ItemClickHandler {
            override fun onItemClicked(item: Item) {
                (activity as OrderActivity).startItemPreviewActivity(item.itemId)
            }
        })

        binding.apply {
            foodRv.apply {
                this.adapter = adapter
                setHasFixedSize(true)
            }
        }

        viewModel.foodList.observe(viewLifecycleOwner) { foodList ->
            foodList?.let { adapter.submitList(it) }
        }
    }

}