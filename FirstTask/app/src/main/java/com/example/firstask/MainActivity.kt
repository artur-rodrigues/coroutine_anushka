package com.example.firstask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var mainJob: Job
    lateinit var ioJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainJob = CoroutineScope(Dispatchers.Main).launch {
            Log.i("MyTag", "Rodando na main thread: ${Thread.currentThread().name}")
        }

        ioJob = CoroutineScope(Dispatchers.IO).launch {
            Log.i("MyTag", "Rodando na outra thread: ${Thread.currentThread().name}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if (::mainJob.isInitialized) {
            mainJob.cancel()
        }

        if (::ioJob.isInitialized) {
            ioJob.cancel()
        }
    }
}
