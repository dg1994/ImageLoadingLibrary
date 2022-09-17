package com.example.sampleimageloading.data.cache

import android.graphics.Bitmap
import android.util.Log

class ImageCacheRepository(
    val memoryCacheDataSource: ImageCacheDataSource,
    val diskCacheDataSource: ImageCacheDataSource
) {

    suspend fun getBitmap(url: String): Bitmap? {
        val bitMapInMemory = memoryCacheDataSource.getImage(url)
        if (bitMapInMemory != null) {
            return bitMapInMemory
        } else {
            val bitMapInDisk = diskCacheDataSource.getImage(url)
            if (bitMapInDisk != null) {
                memoryCacheDataSource.putImage(url, bitMapInDisk)
            }
            return bitMapInDisk
        }
    }

    suspend fun putBitmap(url: String, bitmap: Bitmap) {
        diskCacheDataSource.putImage(url, bitmap)
        memoryCacheDataSource.putImage(url, bitmap)
    }

}