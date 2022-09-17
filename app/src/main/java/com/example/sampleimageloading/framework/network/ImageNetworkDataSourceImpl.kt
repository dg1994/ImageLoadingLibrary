package com.example.sampleimageloading.framework.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.example.sampleimageloading.data.network.ImageNetworkDataSource
import com.example.sampleimageloading.framework.network.api.ImageApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.lang.Exception


class ImageNetworkDataSourceImpl(val apiService: ImageApiService): ImageNetworkDataSource {

    private val TAG: String = "AppDebug"

    override suspend fun getImage(url: String): Bitmap? {
        return withContext(IO) {
            try {
                val responseBody = apiService.getImageBitmap(url).execute().body()
                BitmapFactory.decodeStream(responseBody?.byteStream())
            } catch (e: Exception) {
                Log.d(TAG, "exception in fetching from network $e")
                null
            }
        }
    }
}