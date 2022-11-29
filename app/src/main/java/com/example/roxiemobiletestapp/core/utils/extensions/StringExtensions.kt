/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.core.utils.extensions

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String?.toDate(): String {
    this?.let {
        try {
            val date =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault()).parse(this)
            return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(date)
        } catch (e: ParseException) {
            Log.d("StringExtension", "toDate: ${e.printStackTrace()}")
        }
    }
    return "Unknown"
}

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
fun String?.toClock(): String {
    this?.let {
        try {
            val date =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault()).parse(this)
            return SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(date)
        } catch (e: ParseException) {
            Log.d("StringExtension", "toDate: ${e.printStackTrace()}")
        }
    }
    return "Unknown"
}