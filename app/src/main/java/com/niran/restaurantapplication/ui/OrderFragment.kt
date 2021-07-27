package com.niran.restaurantapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.niran.restaurantapplication.OrderActivity
import com.niran.restaurantapplication.RestaurantApplication
import com.niran.restaurantapplication.database.models.Item
import com.niran.restaurantapplication.databinding.FragmentOrderBinding
import com.niran.restaurantapplication.utils.ItemAdapter
import com.niran.restaurantapplication.viewmodels.OrderViewModel
import com.niran.restaurantapplication.viewmodels.OrderViewModelFactory


class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding

    private val viewModel: OrderViewModel by viewModels {
        OrderViewModelFactory((activity?.application as RestaurantApplication).itemRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOrderBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemAdapter(object : ItemAdapter.ItemClickHandler {
            override fun onItemClicked(item: Item) {
                (activity as OrderActivity).handler.startItemPreviewActivity(item.itemId)
            }
        })

        binding.apply {
            orderRv.adapter = adapter

            addMenuItemFab.setOnClickListener { navigateToMenuFragment() }

            clearOrderBtn.setOnClickListener { viewModel.deleteAllOrderedItems() }
        }

        viewModel.itemList.observe(viewLifecycleOwner) { itemList ->
            itemList?.let { adapter.submitList(it) }
        }

    }

    private fun navigateToMenuFragment() = view?.findNavController()
        ?.navigate(OrderFragmentDirections.actionOrderFragmentToMenuFragment())
}