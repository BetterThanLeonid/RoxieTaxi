/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.presentation.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roxiemobiletestapp.core.utils.ErrorsEnum
import com.example.roxiemobiletestapp.core.utils.Resource
import com.example.roxiemobiletestapp.feature_main.domain.model.OrderModel
import com.example.roxiemobiletestapp.feature_main.domain.use_case.GetOrdersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val getOrdersUseCase: GetOrdersUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<List<OrderModel>?>(null)
    val state: StateFlow<List<OrderModel>?> = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow: SharedFlow<UIEvent> = _eventFlow.asSharedFlow()


    fun getOrders() {
        viewModelScope.launch {
            getOrdersUseCase()
                .onEach { result ->
                    when (result) {
                        is Resource.Error -> {
                            _eventFlow.emit(UIEvent.ShowError(error = result.error))
                        }
                        is Resource.Loading -> {
                            _eventFlow.emit(UIEvent.ShowProgress)
                        }
                        is Resource.Success -> {
                            _state.value = result.data
                        }
                    }
                }
                .launchIn(this)
        }
    }

    sealed class UIEvent {
        object ShowProgress : UIEvent()
        data class ShowError(val error: ErrorsEnum?) : UIEvent()
    }
}