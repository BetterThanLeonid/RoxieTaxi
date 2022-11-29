/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class PriceDto(
    @SerializedName("amount") val amount: Int,
    @SerializedName("currency") val currency: String,
) {
    fun asFinanceString(): String {
        val fractionDigits = Currency.getInstance(currency).defaultFractionDigits
        val price = amount.toString()
        val majorUnit = price.substring(0, price.length - fractionDigits)
        val minorUnit = price.substring(price.length - fractionDigits, price.length)
        return "$majorUnit.$minorUnit $currency"
    }
}