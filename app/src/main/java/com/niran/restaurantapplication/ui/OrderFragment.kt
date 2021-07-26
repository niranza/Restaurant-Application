package com.niran.restaurantapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.niran.restaurantapplication.databinding.FragmentOrderBinding


class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOrderBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            addMenuItemFab.setOnClickListener {
                navigateToMenuFragment()
            }
        }

    }

    private fun navigateToMenuFragment() = view?.findNavController()
        ?.navigate(OrderFragmentDirections.actionOrderFragmentToMenuFragment())
}