/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.presentation.orders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roxiemobiletestapp.R
import com.example.roxiemobiletestapp.core.utils.item_decorations.VerticalItemDecoration
import com.example.roxiemobiletestapp.databinding.FragmentOrdersBinding
import com.example.roxiemobiletestapp.feature_main.domain.model.OrderModel
import com.example.roxiemobiletestapp.ui.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {

    private val viewModel by viewModels<OrdersViewModel>()
    private val ordersAdapter = OrdersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initObservers()

        if (viewModel.state.value == null) {
            viewModel.getOrders()
        }
    }

    private fun initRecycler() {
        binding.rvOrders.apply {
            this.adapter = ordersAdapter
            this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(VerticalItemDecoration(includeTopPadding = true))
        }

        ordersAdapter.onItemClick(object : OrdersAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                moveToDetailsScreen(ordersAdapter.data[position])
            }
        })
    }

    private fun moveToDetailsScreen(order: OrderModel) = findNavController().navigate(
        OrdersFragmentDirections.actionOrdersFragmentToOrderDetailsFragment(
            order = order
        )
    )

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                state?.let {
                    ordersAdapter.data = state
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect { event ->
                binding.progress.isVisible = event is OrdersViewModel.UIEvent.ShowProgress
                when (event) {
                    is OrdersViewModel.UIEvent.ShowError ->
                        Snackbar.make(requireView(),
                            event.error?.resId ?: R.string.error_unknown,
                            Snackbar.LENGTH_LONG).show()
                    else -> {}
                }
            }
        }
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentOrdersBinding.inflate(inflater, container, false)
}