package com.niran.restaurantapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.niran.restaurantapplication.RestaurantApplication
import com.niran.restaurantapplication.databinding.FragmentBeverageBinding
import com.niran.restaurantapplication.utils.ItemAdapter
import com.niran.restaurantapplication.viewmodels.BeverageViewModel
import com.niran.restaurantapplication.viewmodels.BeverageViewModelFactory


class BeverageFragment : Fragment() {

    private lateinit var binding: FragmentBeverageBinding

    private val viewModel: BeverageViewModel by viewModels {
        BeverageViewModelFactory((activity?.application as RestaurantApplication).itemRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBeverageBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemAdapter()

        binding.apply {
            beverageRv.adapter = adapter
        }

        viewModel.beverageList.observe(viewLifecycleOwner) { beverageList ->
            beverageList?.let { adapter.submitList(it) }
        }

    }
}