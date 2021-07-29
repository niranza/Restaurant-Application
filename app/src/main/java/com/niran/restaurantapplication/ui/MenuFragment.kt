package com.niran.restaurantapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.niran.restaurantapplication.databinding.FragmentMenuBinding
import com.niran.restaurantapplication.utils.FoodTypes
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
            FoodFragment(),
            BeverageFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.apply {
            menuVp.adapter = adapter
            checkoutBtn.setOnClickListener { view.findNavController().navigateUp() }
            TabLayoutMediator(menuTbl, menuVp) { tab, position ->
                tab.text = getString(FoodTypes.values()[position].titleId)
            }.attach()
        }

    }
}