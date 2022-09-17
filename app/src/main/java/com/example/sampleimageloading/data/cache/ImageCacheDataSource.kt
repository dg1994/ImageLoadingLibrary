package com.example.sampleimageloading.data.cache

import android.graphics.Bitmap
import androidx.lifecycle.LiveData

interface ImageCacheDataSource {
    suspend fun putImage(url: String, bitmap: Bitmap)
    suspend fun getImage(url: String): Bitmap?
    suspend fun clear()
}