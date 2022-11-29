/*
 * Created by Nedushny at 28/11/2022.
 */

package com.example.roxiemobiletestapp.core.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.example.roxiemobiletestapp.core.utils.extensions.isFileExists
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class FileManager @Inject constructor(private val context: Context) {

    private val photoPath = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)

    fun savePicture(response: ResponseBody, photoKey: String): Uri {
        val file = File(photoPath, photoKey)
        val os = FileOutputStream(file)
        os.write(response.bytes())
        os.close()
        return Uri.fromFile(file)
    }

    fun isPictureExists(photoKey: String): Boolean =
        photoPath?.listFiles()?.any { it.name == photoKey } ?: false

    fun getUri(photoKey: String): Uri {
        return Uri.fromFile(File(photoPath, photoKey))
    }

    fun isFileExpired(uri: Uri): Boolean {
        val file = File(uri.path.toString())
        return if (file.isFileExists) {
            val createdTime = file.lastModified()
            val outdatedTime = createdTime + 10 * 60 * 1000
            val nowTime = System.currentTimeMillis()
            Log.e("TAG", "isFileExpired: createdTime = $createdTime for ${uri.path}")
            Log.e("TAG", "isFileExpired: outdatedTime = $outdatedTime")
            Log.e("TAG", "isFileExpired: nowTime = $nowTime")
            return nowTime >= outdatedTime
        } else false
    }

}
