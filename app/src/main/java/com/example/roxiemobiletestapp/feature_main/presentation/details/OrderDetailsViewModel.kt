/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.presentation.details

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roxiemobiletestapp.core.utils.ErrorsEnum
import com.example.roxiemobiletestapp.core.utils.Resource
import com.example.roxiemobiletestapp.feature_main.domain.use_case.GetImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailsViewModel @Inject constructor(
    private val getImageUseCase: GetImageUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<Uri?>(null)
    val state: StateFlow<Uri?> = _state.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow: SharedFlow<UIEvent> = _eventFlow.asSharedFlow()

    fun getImage(photoKey: String) {
        viewModelScope.launch {
            getImageUseCase(photoKey)
                .onEach { result ->
                    when (result) {
                        is Resource.Error -> {
                            _eventFlow.emit(UIEvent.ShowError(error = result.error))
                        }
                        is Resource.Loading -> {}
                        is Resource.Success -> {
                            _state.value = result.data
                        }
                    }
                }
                .launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowError(val error: ErrorsEnum?) : UIEvent()
    }
}