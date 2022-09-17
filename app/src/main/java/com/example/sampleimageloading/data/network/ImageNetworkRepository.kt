package com.example.sampleimageloading.data.network

import android.graphics.Bitmap

class ImageNetworkRepository(val imageNetworkDataSource: ImageNetworkDataSource) {

    suspend fun getImage(url: String): Bitmap? {
        return imageNetworkDataSource.getImage(url)
    }
}