package com.example.sampleimageloading.data.network

import android.graphics.Bitmap

interface ImageNetworkDataSource {
    suspend fun getImage(url: String): Bitmap?
}