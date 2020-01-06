package com.anushka.blockingbuilderdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

           CoroutineScope(Dispatchers.IO).launch {
               for (i in 1..10) {
                   delay(100)
                   Log.i("MyTag", "round $i in ${Thread.currentThread().name}")
               }
             }

    }
}
