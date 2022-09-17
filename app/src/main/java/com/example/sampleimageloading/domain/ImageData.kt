package com.example.sampleimageloading.domain

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_data")
data class ImageData(

    @PrimaryKey
    @ColumnInfo(name="url")
    val url: String,

    @ColumnInfo(name ="bitmap")
    val bitmap: String? = null
    ) {
}