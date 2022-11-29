/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.domain.use_case

import com.example.roxiemobiletestapp.core.utils.Resource
import com.example.roxiemobiletestapp.feature_main.domain.model.OrderModel
import com.example.roxiemobiletestapp.feature_main.domain.repository.TaxiRepository
import kotlinx.coroutines.flow.Flow

class GetOrdersUseCase(
    private val repository: TaxiRepository,
) {
    operator fun invoke(): Flow<Resource<List<OrderModel>>> =
        repository.getOrders()
}
