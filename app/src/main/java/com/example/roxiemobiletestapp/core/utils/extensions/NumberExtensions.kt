/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.core.utils.extensions

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt

val Number.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).roundToInt()