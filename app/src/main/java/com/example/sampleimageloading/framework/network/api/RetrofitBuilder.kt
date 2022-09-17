package com.example.sampleimageloading.framework.network.api

import retrofit2.Retrofit

object RetrofitBuilder {
    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
    }

    val apiService: ImageApiService by lazy {
        retrofitBuilder
            .baseUrl("https://cdn.pixabay.com/")
            .build()
            .create(ImageApiService::class.java)
    }
}