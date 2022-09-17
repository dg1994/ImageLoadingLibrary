package com.example.sampleimageloading.framework.cache

import android.graphics.Bitmap
import com.example.sampleimageloading.data.cache.ImageCacheDataSource
import com.example.sampleimageloading.domain.ImageData
import com.example.sampleimageloading.domain.util.BitmapUtils
import com.example.sampleimageloading.framework.cache.db.BitmapDao
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class DiskCacheDataSource(private val bitmapDao: BitmapDao): ImageCacheDataSource {

    override suspend fun putImage(url: String, bitmap: Bitmap) {
        withContext(IO) {
            bitmapDao.insert(ImageData(url, BitmapUtils.convertBitmapToString(bitmap)))
        }
    }

    override suspend fun getImage(url: String): Bitmap? {
        return withContext(IO) {
            val list = bitmapDao.getBitmap(url)
            if (list != null && list.isNotEmpty()) {
                BitmapUtils.convertStringToBitmap(list.get(0).bitmap)
            } else {
                null
            }
        }
    }

    override suspend fun clear() {
        withContext(IO) {
            bitmapDao.deleteAllBitmaps()
        }
    }
}