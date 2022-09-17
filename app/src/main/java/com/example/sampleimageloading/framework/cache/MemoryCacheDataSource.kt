package com.example.sampleimageloading.framework.cache

import android.graphics.Bitmap
import android.util.Log
import android.util.LruCache
import androidx.core.graphics.get
import com.example.sampleimageloading.data.cache.ImageCacheDataSource

class MemoryCacheDataSource: ImageCacheDataSource {

    private val cache : LruCache<String, Bitmap>
    init {
        val cacheSize : Int = 20 * 1024 * 1024
        cache = object : LruCache<String, Bitmap>(cacheSize) {
            override fun sizeOf(key: String, value: Bitmap): Int {
                return (value.rowBytes)*(value.height)/1024
            }
        }
    }

    override suspend fun putImage(url: String, bitmap: Bitmap) {
        cache.put(url, bitmap)
    }

    override suspend fun getImage(url: String): Bitmap? {
        return cache.get(url)
    }

    override suspend fun clear() {
        cache.evictAll()
    }
}