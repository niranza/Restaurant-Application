package com.niran.restaurantapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.niran.restaurantapplication.OrderActivity
import com.niran.restaurantapplication.databinding.FragmentMenuBinding
import com.niran.restaurantapplication.utils.ViewPagerAdapter

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMenuBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = arrayListOf(
            FoodFragment((activity as OrderActivity).handler),
            BeverageFragment((activity as OrderActivity).handler)
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.menuVp.adapter = adapter

    }
}