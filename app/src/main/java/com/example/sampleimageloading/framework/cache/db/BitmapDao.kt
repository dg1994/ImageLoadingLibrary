package com.example.sampleimageloading.framework.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sampleimageloading.domain.ImageData

@Dao
interface BitmapDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(imageData: ImageData): Long

    @Query("SELECT * FROM image_data WHERE url = :url")
    suspend fun getBitmap(url: String): List<ImageData>?

    @Query("DELETE FROM image_data WHERE url = :url")
    suspend fun deleteBitmap(url: String)

    @Query("DELETE FROM image_data")
    suspend fun deleteAllBitmaps()
}