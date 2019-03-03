package com.madgeekfactory.dumblauncher.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madgeekfactory.dumblauncher.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onBackPressed() {
        // do nothing
    }
}
