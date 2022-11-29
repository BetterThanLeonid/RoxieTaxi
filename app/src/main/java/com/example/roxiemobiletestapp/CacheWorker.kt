/*
 * Created by Nedushny at 29/11/2022.
 */

package com.example.roxiemobiletestapp

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.roxiemobiletestapp.core.utils.Constants
import com.example.roxiemobiletestapp.core.utils.FileManager
import com.example.roxiemobiletestapp.core.utils.extensions.isFileExists
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@HiltWorker
class CacheWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val fileManager: FileManager,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val key = workerParams.inputData.getString(Constants.PHOTO_KEY_WORKER)
            key?.let {
                val uri = fileManager.getUri(key)
                val file = File(uri.path.toString())
                if (file.isFileExists) {
                    file.delete()
                    return@withContext Result.success()
                } else {
                    return@withContext Result.failure()
                }
            }
            return@withContext Result.failure()
        }
    }
}