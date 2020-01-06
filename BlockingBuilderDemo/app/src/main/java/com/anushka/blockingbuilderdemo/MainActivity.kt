package com.anushka.blockingbuilderdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("MyTag", "this is the start of the onCreate")

        runBlocking(Dispatchers.IO) {
            for (i in 1..10) {
                delay(100)
                Log.i("MyTag", "round $i in ${Thread.currentThread().name}")
            }
        }

        Log.i("MyTag", "this is the end 1 of the onCreate")
        Log.i("MyTag", "this is the end 2 of the onCreate")
    }
}
