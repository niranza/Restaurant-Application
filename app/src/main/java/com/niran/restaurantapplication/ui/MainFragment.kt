package com.niran.restaurantapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.niran.restaurantapplication.OrderActivity
import com.niran.restaurantapplication.R
import com.niran.restaurantapplication.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater)

        setHasOptionsMenu(true)

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.main_menu, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.about_item -> {
                navigateToABoutFragment()
                true
            }
            else -> true
        }

    private fun navigateToABoutFragment() = view?.findNavController()
        ?.navigate(MainFragmentDirections.actionMainFragmentToAboutFragment())

}