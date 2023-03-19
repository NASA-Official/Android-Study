package com.nassafy.aro.hilt.practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nassafy.aro.hilt.practice.adapter.AnalyticsAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}