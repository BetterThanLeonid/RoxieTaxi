/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderModel(
    val startAddress: String,
    val endAddress: String,
    val orderTime: String,
    val amount: String,
    val vehicle: VehicleModel,
) : Parcelable