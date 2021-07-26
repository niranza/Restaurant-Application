package com.niran.restaurantapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.niran.restaurantapplication.OrderActivity
import com.niran.restaurantapplication.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            orderBtn.setOnClickListener { startOrderActivity() }
        }

    }

    private fun startOrderActivity() {
        val intent = Intent(activity, OrderActivity::class.java)
        startActivity(intent)
    }

}