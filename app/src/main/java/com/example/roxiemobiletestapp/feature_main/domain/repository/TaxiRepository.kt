/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.domain.repository

import android.net.Uri
import com.example.roxiemobiletestapp.core.utils.Resource
import com.example.roxiemobiletestapp.feature_main.domain.model.OrderModel
import kotlinx.coroutines.flow.Flow

interface TaxiRepository {
    fun getOrders(): Flow<Resource<List<OrderModel>>>
    fun getImage(photoKey: String): Flow<Resource<Uri>>
}
