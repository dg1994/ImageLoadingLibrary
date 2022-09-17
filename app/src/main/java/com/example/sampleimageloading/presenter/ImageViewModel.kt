package com.example.sampleimageloading.presenter

import android.app.Application
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.example.sampleimageloading.data.cache.ImageCacheRepository
import com.example.sampleimageloading.data.network.ImageNetworkRepository
import com.example.sampleimageloading.framework.cache.DiskCacheDataSource
import com.example.sampleimageloading.framework.cache.MemoryCacheDataSource
import com.example.sampleimageloading.framework.cache.db.AppDatabase
import com.example.sampleimageloading.framework.network.ImageNetworkDataSourceImpl
import com.example.sampleimageloading.framework.network.api.RetrofitBuilder
import com.example.sampleimageloading.interactors.GetBitmap
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ImageViewModel(app: Application): AndroidViewModel(app) {
    private val TAG: String = "AppDebug"
    private val _bitmapLiveData: MutableLiveData<Bitmap?> = MutableLiveData()
    val bitmapLiveData: LiveData<Bitmap?>
        get() = _bitmapLiveData


    val appDatabase: AppDatabase = Room
        .databaseBuilder(app, AppDatabase::class.java, "DATABASE_NAME")
        .fallbackToDestructiveMigration() // get correct db version if schema changed
        .build()
    val memoryCacheDataSource = MemoryCacheDataSource()
    val diskCacheDataSource = DiskCacheDataSource(appDatabase.getBitmapDao())
    val imageCacheRepository = ImageCacheRepository(memoryCacheDataSource, diskCacheDataSource)

    val apiService = RetrofitBuilder.apiService
    val imageNetworkDataSource = ImageNetworkDataSourceImpl(apiService)
    val imageNetworkRepository = ImageNetworkRepository(imageNetworkDataSource)

    val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG,"CoroutineExceptionHandler got $exception")
    }

    fun getBitmap(url: String) {
        viewModelScope.launch(exceptionHandler) {
            val bitmap = GetBitmap(imageCacheRepository, imageNetworkRepository).getBitmap(url)
            _bitmapLiveData.value = bitmap
        }
    }
}