/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.core.utils

import androidx.annotation.StringRes
import com.example.roxiemobiletestapp.R

enum class ErrorsEnum(@StringRes val resId: Int) {
    NO_CONNECTION(R.string.error_no_internet_connection),
    UNKNOWN(R.string.error_unknown),
    IMAGE_NOT_LOADED(R.string.error_image_not_loaded)
}
