/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.data.remote.dto

import com.example.roxiemobiletestapp.feature_main.domain.model.OrderModel

data class OrderDto(
    val endAddress: AddressDto,
    val id: Int,
    val orderTime: String,
    val price: PriceDto,
    val startAddress: AddressDto,
    val vehicle: VehicleDto,
) {
    fun mapToOrderModel() = OrderModel(
        startAddress = "${startAddress.city}, ${startAddress.address}",
        endAddress = "${endAddress.city}, ${endAddress.address}",
        amount = price.asFinanceString(),
        orderTime = orderTime,
        vehicle = vehicle.mapToVehicleModel()
    )
}