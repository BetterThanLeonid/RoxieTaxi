/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.data.remote.api

import com.example.roxiemobiletestapp.feature_main.data.remote.dto.OrderDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface TaxiApi {

    @GET("careers/test/orders.json")
    suspend fun getOrders(): ArrayList<OrderDto>

    @GET("careers/test/images/{key}")
    suspend fun getImage(
        @Path("key") photoKey: String,
    ): ResponseBody
}
