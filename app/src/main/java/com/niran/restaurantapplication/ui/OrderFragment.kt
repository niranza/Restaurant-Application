package com.niran.restaurantapplication.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.niran.restaurantapplication.OrderActivity
import com.niran.restaurantapplication.R
import com.niran.restaurantapplication.RestaurantApplication
import com.niran.restaurantapplication.database.models.Item
import com.niran.restaurantapplication.databinding.FragmentOrderBinding
import com.niran.restaurantapplication.utils.ItemAdapter
import com.niran.restaurantapplication.utils.MENU_NOTIFICATION_ID
import com.niran.restaurantapplication.utils.NotificationsUtil
import com.niran.restaurantapplication.viewmodels.OrderViewModel
import com.niran.restaurantapplication.viewmodels.OrderViewModelFactory


class OrderFragment : Fragment() {

    private lateinit var binding: FragmentOrderBinding

    private var hasOrderedItems = false

    private val viewModel: OrderViewModel by viewModels {
        OrderViewModelFactory((activity?.application as RestaurantApplication).itemRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentOrderBinding.inflate(inflater)

        setHasOptionsMenu(true)

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
            orderRv.adapter = adapter

            addMenuItemFab.setOnClickListener { navigateToMenuFragment() }

            clearOrderBtn.setOnClickListener { clearOrder() }
        }

        viewModel.itemList.observe(viewLifecycleOwner) { itemList ->
            itemList?.let {
                adapter.submitList(it)
                hasOrderedItems = it.isNotEmpty()
                binding.noItemsTv.visibility = if (hasOrderedItems) View.GONE else View.VISIBLE
            }
        }

    }

    private fun clearOrder() {
        if (!hasOrderedItems) return
        AlertDialog.Builder(activity).apply {
            setTitle(R.string.clear_order_alert_title)
            setPositiveButton(R.string.clear) { _, _ -> viewModel.deleteAllOrderedItems() }
            setNegativeButton(R.string.back) { _, _ -> }
            show()
        }
    }

    private fun navigateToMenuFragment() = view?.findNavController()
        ?.navigate(OrderFragmentDirections.actionOrderFragmentToMenuFragment())

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
        inflater.inflate(R.menu.order_menu, menu)

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.back_item -> {
                activity?.finish()
                true
            }
            else -> true
        }


    override fun onDestroy() {
        super.onDestroy()
        if (hasOrderedItems)
            NotificationsUtil.sendNotification(
                R.string.menu_channel_id,
                MENU_NOTIFICATION_ID,
                R.string.menu_notification_title,
                R.string.menu_notification_text,
                Intent(activity, OrderActivity::class.java),
                requireContext()
            )
    }
}