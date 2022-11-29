/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.data.remote.dto

import com.example.roxiemobiletestapp.feature_main.domain.model.VehicleModel
import com.google.gson.annotations.SerializedName

data class VehicleDto(
    @SerializedName("regNumber") val regNumber: String,
    @SerializedName("modelName") val modelName: String,
    @SerializedName("photo") val photo: String,
    @SerializedName("driverName") val driverName: String,
) {
    fun mapToVehicleModel() = VehicleModel(
        number = regNumber,
        model = modelName,
        photoKey = photo,
        driver = driverName
    )
}