package com.example.sampleimageloading.domain.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

import java.io.ByteArrayOutputStream




class BitmapUtils {
    companion object {
        fun convertBitmapToString(bitmap: Bitmap): String {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray = stream.toByteArray()
            val bitmapString: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
            return bitmapString
        }

        fun convertStringToBitmap(bitmapString: String?): Bitmap? {
            if (bitmapString == null)
                return null
            else {
                val byteArray: ByteArray = Base64.decode(bitmapString, Base64.DEFAULT)
                return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            }
        }
    }
}