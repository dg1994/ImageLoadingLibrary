package com.example.sampleimageloading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sampleimageloading.presenter.ImageViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    lateinit var imageViewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewModel = ViewModelProvider(this).get(ImageViewModel::class.java)

        imageViewModel.bitmapLiveData.observe(this, Observer { bitmap ->
            bitmap?.let {
                image.setImageBitmap(it)
            }
        })

        text.setOnClickListener {
            imageViewModel.getBitmap("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg")
        }
    }
}