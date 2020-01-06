package com.anushka.coroutinesdemo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var userJob: Job
    private lateinit var locationJob: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCount.setOnClickListener {
            tvCount.text = count++.toString()
        }
        btnDownloadUserData.setOnClickListener {
            userJob = CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }
        }
        btnDownloadLocationData.setOnClickListener {
            locationJob = CoroutineScope(Dispatchers.IO).launch {
                downloadLocationData()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::userJob.isInitialized) {
            userJob.cancel()
        }

        if(::locationJob.isInitialized) {
            locationJob.cancel()
        }
    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {
//            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
            withContext(Dispatchers.Main) {
                tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"
            }
        }
    }

    private suspend fun downloadLocationData() {
        for (i in 1..200000) {
//            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
            withContext(Dispatchers.Main) {
                tvLocationMessage.text = "Downloading location $i in ${Thread.currentThread().name}"
            }

            delay(300)
        }
    }
}
