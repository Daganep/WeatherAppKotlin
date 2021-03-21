package com.penkin.weatherappkotlin.view

import android.os.Bundle
import com.penkin.weatherappkotlin.R
import moxy.MvpAppCompatActivity

class MainActivity : MvpAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}