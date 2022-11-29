/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.feature_main.data.remote.repository

import android.net.Uri
import com.example.roxiemobiletestapp.core.utils.ErrorsEnum
import com.example.roxiemobiletestapp.core.utils.FileManager
import com.example.roxiemobiletestapp.core.utils.Resource
import com.example.roxiemobiletestapp.feature_main.data.remote.api.TaxiApi
import com.example.roxiemobiletestapp.feature_main.domain.model.OrderModel
import com.example.roxiemobiletestapp.feature_main.domain.repository.TaxiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class TaxiRepositoryImpl(
    private val api: TaxiApi,
    private val fileManager: FileManager,
) : TaxiRepository {
    override fun getOrders(): Flow<Resource<List<OrderModel>>> = flow {
        emit(Resource.Loading())
        try {
            val response = api.getOrders()
            emit(Resource.Success(response.map { it.mapToOrderModel() }))
        } catch (e: HttpException) {
            emit(Resource.Error(error = ErrorsEnum.UNKNOWN))
        } catch (e: IOException) {
            emit(Resource.Error(error = ErrorsEnum.NO_CONNECTION))
        }
    }

    override fun getImage(photoKey: String): Flow<Resource<Uri>> = flow {
        try {
            val response = api.getImage(photoKey)
            val uri = fileManager.savePicture(response, photoKey)
            emit(Resource.Success(data = uri))
        } catch (e: HttpException) {
            emit(Resource.Error(error = ErrorsEnum.IMAGE_NOT_LOADED))
        } catch (e: IOException) {
            emit(Resource.Error(error = ErrorsEnum.NO_CONNECTION))
        }
    }

}