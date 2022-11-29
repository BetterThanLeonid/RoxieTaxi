/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.roxiemobiletestapp.CacheWorker
import com.example.roxiemobiletestapp.R
import com.example.roxiemobiletestapp.core.utils.Constants
import com.example.roxiemobiletestapp.core.utils.FileManager
import com.example.roxiemobiletestapp.core.utils.extensions.toClock
import com.example.roxiemobiletestapp.databinding.FragmentOrderDetailsBinding
import com.example.roxiemobiletestapp.ui.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class OrderDetailsFragment : BaseFragment<FragmentOrderDetailsBinding>() {

    private val viewModel by viewModels<OrderDetailsViewModel>()
    private val args by navArgs<OrderDetailsFragmentArgs>()

    @Inject
    lateinit var fileManager: FileManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        setupToolbar()
        fillDetails()
    }

    private fun fillDetails() {
        binding.details.startAddress.text = args.order.startAddress
        binding.details.endAddress.text = args.order.endAddress
        binding.details.carModel.text = args.order.vehicle.model
        binding.details.carNumber.text = args.order.vehicle.number
        binding.details.driver.text = args.order.vehicle.driver
        binding.details.date.text = args.order.orderTime.toClock()
        binding.details.amount.text = args.order.amount

        if (fileManager.isPictureExists(args.order.vehicle.photoKey)) {
            binding.image.setImageURI(fileManager.getUri(args.order.vehicle.photoKey))
        } else {
            viewModel.getImage(args.order.vehicle.photoKey)
        }
    }

    private fun setupToolbar() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.toolbar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_round_arrow_back_ios_new_24)
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { uri ->
                uri?.let {
                    binding.image.setImageURI(uri)
                    setupWorker(args.order.vehicle.photoKey)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collectLatest { event ->
                if (event is OrderDetailsViewModel.UIEvent.ShowError)
                    Snackbar.make(requireView(),
                        event.error?.resId ?: R.string.error_unknown,
                        Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupWorker(photoKey: String) {
        val data = Data.Builder()
        data.putString(Constants.PHOTO_KEY_WORKER, photoKey)
        val workRequest = OneTimeWorkRequestBuilder<CacheWorker>()
            .setInitialDelay(Constants.TIME_TO_DELETE, TimeUnit.MINUTES)
            .setInputData(data.build())
            .build()
        val workManager = WorkManager.getInstance(requireContext().applicationContext)
        workManager.enqueue(workRequest)
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentOrderDetailsBinding.inflate(inflater, container, false)
}