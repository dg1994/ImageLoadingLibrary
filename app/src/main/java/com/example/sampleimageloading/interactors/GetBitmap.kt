package com.example.sampleimageloading.interactors

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.sampleimageloading.data.cache.ImageCacheRepository
import com.example.sampleimageloading.data.network.ImageNetworkRepository

class GetBitmap(val imageCacheRepository: ImageCacheRepository, val imageNetworkRepository: ImageNetworkRepository) {

    suspend fun getBitmap(url: String): Bitmap? {
        val bitmapInCache = imageCacheRepository.getBitmap(url)
        if (bitmapInCache != null) {
            return bitmapInCache
        } else {
            val bitmapFromNetwork = imageNetworkRepository.getImage(url)
            if (bitmapFromNetwork != null) {
                imageCacheRepository.putBitmap(url, bitmapFromNetwork)
            }
            return bitmapFromNetwork
        }
    }
}