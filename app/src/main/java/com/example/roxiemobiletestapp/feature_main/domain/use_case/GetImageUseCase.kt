/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.domain.use_case

import android.net.Uri
import com.example.roxiemobiletestapp.core.utils.Resource
import com.example.roxiemobiletestapp.feature_main.domain.repository.TaxiRepository
import kotlinx.coroutines.flow.Flow

class GetImageUseCase(
    private val repository: TaxiRepository,
) {
    operator fun invoke(photoKey: String): Flow<Resource<Uri>> =
        repository.getImage(photoKey)
}