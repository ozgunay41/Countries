package com.ozgunay.countries.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ozgunay.countries.R
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        thread {

        }
    }
}