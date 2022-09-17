package com.example.sampleimageloading.framework.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sampleimageloading.domain.ImageData

@Database(entities = [ImageData::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getBitmapDao(): BitmapDao
}