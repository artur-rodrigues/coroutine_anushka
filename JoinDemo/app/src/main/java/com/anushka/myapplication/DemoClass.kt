package com.anushka.myapplication

import android.util.Log
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DemoClass {

    suspend fun test1() {
        coroutineScope {
            log("Top ${Thread.currentThread().name}")
            val job = launch(IO) {
                log("Before Delay ${Thread.currentThread().name}")
                delay(1000)
                log("After Delay ${Thread.currentThread().name}")
            }
            job.join()
            log("Bottom ${Thread.currentThread().name}")
        }
    }

    private fun log(message: String) {
        Log.i("MyTag",message)
    }
}