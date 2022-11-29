/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VehicleModel(
    val number: String,
    val model: String,
    val photoKey: String,
    val driver: String,
) : Parcelable
