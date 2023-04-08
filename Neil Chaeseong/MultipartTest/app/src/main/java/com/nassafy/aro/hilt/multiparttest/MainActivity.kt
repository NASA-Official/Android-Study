package com.nassafy.aro.hilt.multiparttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import coil.transform.GrayscaleTransformation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val svgLink = "https://nassafy.s3.ap-northeast-2.amazonaws.com/%EB%AA%85%EC%86%8C/%EC%8A%A4%EC%9B%A8%EB%8D%B4/%EC%8A%A4%ED%86%A1%ED%99%80%EB%A6%84/colorStamp.svg"

        val imageLoader = ImageLoader.Builder(this)
            .componentRegistry {
                add(SvgDecoder(this@MainActivity))
            }
            .build()

        val request = ImageRequest.Builder(this)
            .data(svgLink)
            .target(imageView)
            .build()


        CoroutineScope(Dispatchers.Main).launch {
            imageLoader.execute(request)
//            val drawable = imageLoader.execute(request).drawable
//            imageView.setImageDrawable(drawable)
        }
    }
}