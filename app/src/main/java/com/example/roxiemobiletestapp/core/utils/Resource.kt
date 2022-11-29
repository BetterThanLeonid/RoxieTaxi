/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.core.utils

sealed class Resource<T>(val data: T? = null, val error: ErrorsEnum? = null) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(data: T? = null, error: ErrorsEnum?) : Resource<T>(data = data, error = error)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}