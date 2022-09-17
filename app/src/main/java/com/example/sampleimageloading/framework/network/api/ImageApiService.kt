package com.example.sampleimageloading.framework.network.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ImageApiService {

    @GET
    fun getImageBitmap(@Url url: String): Call<ResponseBody>
}